package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface MemeRepository {

	Optional<MemeEntity> findByUuid(Connection connection, UUID uuid) throws FindFailedException;

	List<MemeEntity> findAllByUserUuid(Connection connection, UUID uuid) throws FindFailedException;

	void save(Connection connection, MemeEntity memeEntity) throws CreateFailedException;

}
