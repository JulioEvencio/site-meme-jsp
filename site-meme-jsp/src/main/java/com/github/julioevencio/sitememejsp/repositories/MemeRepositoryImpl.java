package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;

public class MemeRepositoryImpl implements MemeRepository {

	@Override
	public void save(Connection connection, MemeEntity memeEntity) throws CreateFailedException {
		String sql = "INSERT INTO tb_memes (uuid, image_uuid, user_uuid, tag_uuid) VALUES (gen_random_uuid(), ?, ?, ?);";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setObject(1, memeEntity.getImage().getUuid());
			stmt.setObject(2, memeEntity.getUser().getUuid());
			stmt.setObject(3, memeEntity.getTag().getUuid());

			stmt.execute();
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
