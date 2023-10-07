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
	public CommentEntity save(CommentEntity commentEntity) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			String sql = "INSERT INTO tb_comments (uuid, comment, user_uuid, meme_uuid) VALUES (gen_random_uuid(), ?, ?, ?);";
			PreparedStatement stmt = connection.prepareStatement(sql);

			stmt.setString(1, commentEntity.getComment());
			stmt.setObject(2, commentEntity.getUser().getUuid());
			stmt.setObject(3, commentEntity.getMeme().getUuid());

			stmt.execute();
			stmt.close();

			return commentEntity;
		} catch (SQLException e) {
			throw new CreateFailedException(e.getMessage());
		}
	}

	@Override
	public List<CommentEntity> findAllByMeme(MemeEntity memeEntity) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			List<CommentEntity> comments = new ArrayList<>();
			String sql = "SELECT * FROM tb_comments WHERE meme_uuid = ?;";

			try (PreparedStatement stmt = connection.prepareStatement(sql)) {
				stmt.setObject(1, memeEntity.getUuid());

				try (ResultSet rs = stmt.executeQuery()) {
					while (rs.next()) {
						CommentEntity comment = new CommentEntity();

						UserEntity user = new UserEntity();
						user.setUuid(UUID.fromString(rs.getString("user_uuid")));

						comment.setUuid(UUID.fromString(rs.getString("uuid")));
						comment.setComment(rs.getString("comment"));
						comment.setUser(user);
						comment.setMeme(memeEntity);

						comments.add(comment);
					}
				}
			}

			return comments;
		} catch (SQLException e) {
			throw new FindFailedException(e.getMessage());
		}
	}

}
