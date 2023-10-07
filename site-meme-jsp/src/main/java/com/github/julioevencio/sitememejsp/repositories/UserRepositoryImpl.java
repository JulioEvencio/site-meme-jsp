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
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public Optional<UserEntity> findByUsername(String username) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Optional<UserEntity> optional = Optional.empty();

			String sql = "SELECT * FROM tb_users WHERE username = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, username);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						UserEntity userEntity = new UserEntity();

						userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						userEntity.setUsername(rs.getString("username"));
						userEntity.setEmail(rs.getString("email"));
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
	public Optional<UserEntity> findByEmail(String email) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Optional<UserEntity> optional = Optional.empty();

			String sql = "SELECT * FROM tb_users WHERE email = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, email);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						UserEntity userEntity = new UserEntity();

						userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						userEntity.setUsername(rs.getString("username"));
						userEntity.setEmail(rs.getString("email"));
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
	public UserEntity save(UserEntity userEntity) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String createUser = "INSERT INTO tb_users (uuid, username, email, password, photo, enabled) VALUES (gen_random_uuid(), ?, ?, ?, ?, ?);";
			String findUuidForUserByUsername = "SELECT uuid FROM tb_users WHERE username = ?;";
			String setRoleForUser = "INSERT INTO tb_users_roles (user_uuid, role_uuid) VALUES (?, ?);";

			try {
				connection.setAutoCommit(false);

				try (PreparedStatement stmt = connection.prepareStatement(createUser)) {
					stmt.setString(1, userEntity.getUsername());
					stmt.setString(2, userEntity.getEmail());
					stmt.setString(3, userEntity.getPassword());
					stmt.setString(4, userEntity.getPhoto());
					stmt.setBoolean(5, userEntity.getEnabled());

					stmt.execute();
				}

				try (PreparedStatement stmt = connection.prepareStatement(findUuidForUserByUsername)) {
					stmt.setString(1, userEntity.getUsername());

					try (ResultSet rs = stmt.executeQuery()) {
						if (rs.next()) {
							userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						}
					}
				}

				for (RoleEntity role : userEntity.getRoles()) {
					try (PreparedStatement stmt = connection.prepareStatement(setRoleForUser)) {
						stmt.setObject(1, userEntity.getUuid());
						stmt.setObject(2, role.getUuid());

						stmt.execute();
					}
				}

				connection.commit();

				return userEntity;
			} catch (SQLException e) {
				connection.rollback();
				throw new CreateFailedException(e.getMessage());
			}
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
