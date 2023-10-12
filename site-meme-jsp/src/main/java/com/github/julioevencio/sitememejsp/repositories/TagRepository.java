package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.TagEntity;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface TagRepository {

	List<TagEntity> findAll(Connection connection) throws FindFailedException;

	Optional<TagEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException;

	Optional<TagEntity> findByName(Connection connection, String name) throws FindFailedException;

}
