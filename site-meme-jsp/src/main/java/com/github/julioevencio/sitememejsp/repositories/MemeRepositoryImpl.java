package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.TagEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class MemeRepositoryImpl implements MemeRepository {
	
	@Override
	public Optional<MemeEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException {
		String sql = "SELECT * FROM tb_memes WHERE uuid = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			Optional<MemeEntity> optional = Optional.empty();

			stmt.setObject(1, uuid);

			try (ResultSet rs = stmt.executeQuery()) {
				if (rs.next()) {
					MemeEntity memeEntity = new MemeEntity();

					ImageEntity imageEntity = new ImageEntity();
					UserEntity userEntity = new UserEntity();
					TagEntity tagEntity = new TagEntity();

					memeEntity.setUuid(UUID.fromString(rs.getString("uuid")));

					imageEntity.setUuid(UUID.fromString(rs.getString("image_uuid")));
					userEntity.setUuid(UUID.fromString(rs.getString("user_uuid")));
					tagEntity.setUuid(UUID.fromString(rs.getString("tag_uuid")));

					memeEntity.setImage(imageEntity);
					memeEntity.setUser(userEntity);
					memeEntity.setTag(tagEntity);

					optional = Optional.of(memeEntity);
				}
			}

			return optional;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

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
