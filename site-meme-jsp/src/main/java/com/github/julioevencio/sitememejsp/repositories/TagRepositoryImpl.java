package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.TagEntity;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class TagRepositoryImpl implements TagRepository {
	
	@Override
	public List<TagEntity> findAll(Connection connection) throws FindFailedException {
		String sql = "SELECT * FROM tb_tags;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			List<TagEntity> tags = new ArrayList<>();

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					TagEntity tagEntity = new TagEntity();

					tagEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					tagEntity.setName(rs.getString("name"));

					tags.add(tagEntity);
				}
			}

			return tags;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public Optional<TagEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException {
		String sql = "SELECT * FROM tb_tags WHERE uuid = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<TagEntity> optional = Optional.empty();

			stmt.setObject(1, uuid);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					TagEntity tagEntity = new TagEntity();

					tagEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					tagEntity.setName(rs.getString("name"));

					optional = Optional.of(tagEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public Optional<TagEntity> findByName(Connection connection, String name) throws FindFailedException {
		String sql = "SELECT * FROM tb_tags WHERE name = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<TagEntity> optional = Optional.empty();

			stmt.setString(1, name);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					TagEntity tagEntity = new TagEntity();

					tagEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					tagEntity.setName(rs.getString("name"));

					optional = Optional.of(tagEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

}
