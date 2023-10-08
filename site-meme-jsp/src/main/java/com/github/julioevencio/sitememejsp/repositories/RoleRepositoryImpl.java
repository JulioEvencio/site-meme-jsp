package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class RoleRepositoryImpl implements RoleRepository {

	@Override
	public Optional<RoleEntity> findByName(String name) throws DatabaseConnectionFailedException, FindFailedException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			Optional<RoleEntity> optional = Optional.empty();
			String sql = "SELECT * FROM tb_roles WHERE name = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setString(1, name);

				try (ResultSet rs = stmt.executeQuery()) {
					if (rs.next()) {
						RoleEntity roleEntity = new RoleEntity();

						roleEntity.setUuid(UUID.fromString(rs.getString("uuid")));
						roleEntity.setName(rs.getString("name"));

						optional = Optional.of(roleEntity);
					}
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

}
