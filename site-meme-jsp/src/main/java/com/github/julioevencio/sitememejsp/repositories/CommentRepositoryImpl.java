package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.CommentEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public class CommentRepositoryImpl implements CommentRepository {

	@Override
	public List<CommentEntity> findByMemeUuid(Connection connection, UUID uuid) throws FindFailedException {
		String sql = "SELECT * FROM tb_comments WHERE meme_uuid = ?;";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			List<CommentEntity> comments = new ArrayList<>();

			stmt.setObject(1, uuid);

			try (ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					CommentEntity commentEntity = new CommentEntity();
					UserEntity userEntity = new UserEntity();
					MemeEntity memeEntity = new MemeEntity();

					commentEntity.setUuid(UUID.fromString(rs.getString("uuid")));
					commentEntity.setComment(rs.getString("comment"));

					userEntity.setUuid(UUID.fromString(rs.getString("user_uuid")));
					commentEntity.setUser(userEntity);

					memeEntity.setUuid(UUID.fromString(rs.getString("meme_uuid")));
					commentEntity.setMeme(memeEntity);

					comments.add(commentEntity);
				}
			}

			return comments;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

	@Override
	public void save(Connection connection, CommentEntity commentEntity) throws CreateFailedException {
		String sql = "INSERT INTO tb_comments (uuid, comment, user_uuid, meme_uuid) VALUES (gen_random_uuid(), ?, ?, ?);";

		try (PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setString(1, commentEntity.getComment());
			stmt.setObject(2, commentEntity.getUser().getUuid());
			stmt.setObject(3, commentEntity.getMeme().getUuid());

			stmt.execute();
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

}
