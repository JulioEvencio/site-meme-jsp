package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.List;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.CommentEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface CommentRepository {

	List<CommentEntity> findByMemeUuid(Connection connection, UUID uuid) throws FindFailedException;

	void save(Connection connection, CommentEntity commentEntity) throws CreateFailedException;

}
