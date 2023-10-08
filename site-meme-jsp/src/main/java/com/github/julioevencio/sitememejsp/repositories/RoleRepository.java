package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface RoleRepository {

	Optional<RoleEntity> findByName(Connection connection, String name) throws FindFailedException;

	List<RoleEntity> findAllByUserUuid(Connection connection, UUID userUuid) throws FindFailedException;

}
