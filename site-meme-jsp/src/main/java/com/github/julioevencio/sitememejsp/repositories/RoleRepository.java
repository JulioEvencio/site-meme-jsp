package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface RoleRepository {

	Optional<RoleEntity> findByName(Connection connection, String name) throws FindFailedException;

}
