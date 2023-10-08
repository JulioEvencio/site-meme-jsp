package com.github.julioevencio.sitememejsp.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import com.github.julioevencio.sitememejsp.dto.auth.LoginRequestDTO;
import com.github.julioevencio.sitememejsp.dto.auth.RegisterRequestDTO;
import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.repositories.ConnectionFactory;
import com.github.julioevencio.sitememejsp.repositories.RoleRepository;
import com.github.julioevencio.sitememejsp.repositories.RoleRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.UserRepository;
import com.github.julioevencio.sitememejsp.repositories.UserRepositoryImpl;

public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;

	public UserServiceImpl() {
		userRepository = new UserRepositoryImpl();
		roleRepository = new RoleRepositoryImpl();
	}

	@Override
	public void register(RegisterRequestDTO dto) throws InvalidDataException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			try {
				connection.setAutoCommit(false);

				if (userRepository.findByUsername(connection, dto.getUsername()).isPresent()) {
					throw new InvalidDataException("Username already!");
				}

				if (userRepository.findByEmail(connection, dto.getEmail()).isPresent()) {
					throw new InvalidDataException("E-mail already!");
				}

				UserEntity userEntity = new UserEntity();
				RoleEntity roleEntity;

				userEntity.setUuid(null);
				userEntity.setUsername(dto.getUsername());
				userEntity.setEmail(dto.getEmail());
				userEntity.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
				userEntity.setPhoto("");
				userEntity.setEnabled(true);

				userRepository.save(connection, userEntity);

				roleEntity = roleRepository.findByName(connection, "ROLE_USER").orElseThrow(() -> new RuntimeException());
				userEntity = userRepository.findByUsername(connection, userEntity.getUsername()).orElseThrow(() -> new RuntimeException());

				userRepository.addRole(connection, userEntity, roleEntity);

				connection.commit();
			} catch (SQLException | FindFailedException | CreateFailedException e) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					throw new RuntimeException();
				}

				throw new RuntimeException();
			}
		} catch (SQLException | DatabaseConnectionFailedException databaseConnectionFailedException) {
			throw new RuntimeException();
		}
	}

	@Override
	public UserSessionDTO login(LoginRequestDTO dto) throws InvalidDataException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			UserEntity userEntity = userRepository.findByEmail(connection, dto.getEmail()).orElseThrow(() -> new InvalidDataException("E-mail not registered!"));

			if (!BCrypt.checkpw(dto.getPassword(), userEntity.getPassword())) {
				throw new InvalidDataException("Incorrect password!");
			}

			List<String> roles = roleRepository.findAllByUserUuid(connection, userEntity.getUuid()).stream().map(role -> role.getName()).toList();

			return new UserSessionDTO(userEntity.getUuid(), userEntity.getUsername(), roles);
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

}
