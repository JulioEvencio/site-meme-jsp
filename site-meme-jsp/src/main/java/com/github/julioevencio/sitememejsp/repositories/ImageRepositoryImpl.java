package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;

public class ImageRepositoryImpl implements ImageRepository {

	@Override
	public ImageEntity save(Connection connection, ImageEntity imageEntity) throws CreateFailedException {
		String sql = "INSERT INTO tb_images (uuid, imageBase64, type) VALUES (gen_random_uuid(), ?, ?) RETURNING uuid;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, imageEntity.getImageBase64());
			stmt.setString(2, imageEntity.getType());

			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				imageEntity.setUuid(UUID.fromString(rs.getString("uuid")));
			}

			return imageEntity;
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
