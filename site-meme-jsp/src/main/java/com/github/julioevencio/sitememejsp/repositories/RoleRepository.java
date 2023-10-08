package com.github.julioevencio.sitememejsp.repositories;

import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface RoleRepository {

	Optional<RoleEntity> findByName(String name) throws DatabaseConnectionFailedException, FindFailedException;

}
