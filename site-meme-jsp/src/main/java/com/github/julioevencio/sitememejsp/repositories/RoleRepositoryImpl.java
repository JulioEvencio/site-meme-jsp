package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class RoleRepositoryImpl implements RoleRepository {

	@Override
	public Optional<RoleEntity> findByName(Connection connection, String name) throws FindFailedException {
		String sql = "SELECT * FROM tb_roles WHERE name = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<RoleEntity> optional = Optional.empty();

			stmt.setString(1, name);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					RoleEntity roleEntity = new RoleEntity();

					roleEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					roleEntity.setName(rs.getString("name"));

					optional = Optional.of(roleEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public List<RoleEntity> findAllByUserUuid(Connection connection, UUID userUuid) throws FindFailedException {
		String sql = "SELECT tb_roles.uuid, tb_roles.name FROM tb_roles, tb_users, tb_users_roles WHERE tb_roles.uuid = tb_users_roles.role_uuid AND tb_users.uuid = tb_users_roles.user_uuid AND tb_users.uuid = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			List<RoleEntity> rolesEntities = new ArrayList<>();

			stmt.setObject(1, userUuid);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					RoleEntity roleEntity = new RoleEntity();

					roleEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					roleEntity.setName(rs.getString("name"));

					rolesEntities.add(roleEntity);
				}
			}

			return rolesEntities;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

}
