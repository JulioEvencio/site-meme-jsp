package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class UserRepositoryImpl implements UserRepository {

	@Override
	public Optional<UserEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException {
		String sql = "SELECT * FROM tb_users WHERE uuid = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<UserEntity> optional = Optional.empty();

			stmt.setObject(1, uuid);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					UserEntity userEntity = new UserEntity();
					ImageEntity imageEntity = new ImageEntity();

					userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					userEntity.setEmail(rs.getString("email"));
					userEntity.setUsername(rs.getString("username"));
					userEntity.setPassword(rs.getString("password"));
					userEntity.setEnabled(rs.getBoolean("enabled"));
					userEntity.setPhoto(imageEntity);
					userEntity.getPhoto().setUuid(UUID.fromString(rs.getString("photo_uuid")));;

					optional = Optional.of(userEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public Optional<UserEntity> findByUsername(Connection connection, String username) throws FindFailedException {
		String sql = "SELECT * FROM tb_users WHERE username = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<UserEntity> optional = Optional.empty();

			stmt.setString(1, username);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					UserEntity userEntity = new UserEntity();
					ImageEntity imageEntity = new ImageEntity();

					userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					userEntity.setEmail(rs.getString("email"));
					userEntity.setUsername(rs.getString("username"));
					userEntity.setPassword(rs.getString("password"));
					userEntity.setEnabled(rs.getBoolean("enabled"));
					userEntity.setPhoto(imageEntity);
					userEntity.getPhoto().setUuid(UUID.fromString(rs.getString("photo_uuid")));;

					optional = Optional.of(userEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public Optional<UserEntity> findByEmail(Connection connection, String email) throws FindFailedException {
		String sql = "SELECT * FROM tb_users WHERE email = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<UserEntity> optional = Optional.empty();

			stmt.setString(1, email);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					UserEntity userEntity = new UserEntity();
					ImageEntity imageEntity = new ImageEntity();

					userEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					userEntity.setEmail(rs.getString("email"));
					userEntity.setUsername(rs.getString("username"));
					userEntity.setPassword(rs.getString("password"));
					userEntity.setEnabled(rs.getBoolean("enabled"));
					userEntity.setPhoto(imageEntity);
					userEntity.getPhoto().setUuid(UUID.fromString(rs.getString("photo_uuid")));;

					optional = Optional.of(userEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public void save(Connection connection, UserEntity userEntity) throws CreateFailedException {
		String sql = "INSERT INTO tb_users (uuid, username, email, password, enabled, photo_uuid) VALUES (gen_random_uuid(), ?, ?, ?, ?, ?);";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, userEntity.getUsername());
			stmt.setString(2, userEntity.getEmail());
			stmt.setString(3, userEntity.getPassword());
			stmt.setBoolean(4, userEntity.getEnabled());
			stmt.setObject(5, userEntity.getPhoto().getUuid());

			stmt.execute();
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

	@Override
	public void addRole(Connection connection, UserEntity userEntity, RoleEntity roleEntity) throws CreateFailedException {
		String sql = "INSERT INTO tb_users_roles (user_uuid, role_uuid) VALUES (?, ?);";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setObject(1, userEntity.getUuid());
			stmt.setObject(2, roleEntity.getUuid());

			stmt.execute();
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
