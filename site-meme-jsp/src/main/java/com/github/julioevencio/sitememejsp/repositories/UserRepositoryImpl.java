package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public Optional<UserEntity> findByUsername(String username) throws DatabaseConnectionFailedException, FindFailedException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Optional<UserEntity> optional = Optional.empty();
			String sql = "SELECT * FROM tb_users WHERE username = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, username);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						UserEntity userEntity = new UserEntity();

						userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						userEntity.setEmail(rs.getString("email"));
						userEntity.setUsername(rs.getString("username"));
						userEntity.setPassword(rs.getString("password"));
						userEntity.setPhoto(rs.getString("photo"));
						userEntity.setEnabled(rs.getBoolean("enabled"));

						optional = Optional.of(userEntity);
					}
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public Optional<UserEntity> findByEmail(String email) throws DatabaseConnectionFailedException, FindFailedException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Optional<UserEntity> optional = Optional.empty();
			String sql = "SELECT * FROM tb_users WHERE email = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, email);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						UserEntity userEntity = new UserEntity();

						userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						userEntity.setEmail(rs.getString("email"));
						userEntity.setUsername(rs.getString("username"));
						userEntity.setPassword(rs.getString("password"));
						userEntity.setPhoto(rs.getString("photo"));
						userEntity.setEnabled(rs.getBoolean("enabled"));

						optional = Optional.of(userEntity);
					}
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public void save(UserEntity userEntity) throws DatabaseConnectionFailedException, CreateFailedException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO tb_users (uuid, username, email, password, photo, enabled) VALUES (gen_random_uuid(), ?, ?, ?, ?, ?);";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, userEntity.getUsername());
				stmt.setString(2, userEntity.getEmail());
				stmt.setString(3, userEntity.getPassword());
				stmt.setString(4, userEntity.getPhoto());
				stmt.setBoolean(5, userEntity.getEnabled());

				stmt.execute();
			}
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

	@Override
	public void addRole(UserEntity userEntity, RoleEntity roleEntity) throws DatabaseConnectionFailedException, CreateFailedException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO tb_users_roles (user_uuid, role_uuid) VALUES (?, ?);";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setObject(1, userEntity.getUuid());
				stmt.setObject(2, roleEntity.getUuid());

				stmt.execute();
			}
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
