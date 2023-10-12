package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface ImageRepository {

	Optional<ImageEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException;

	ImageEntity save(Connection connection, ImageEntity imageEntity) throws CreateFailedException;

}
