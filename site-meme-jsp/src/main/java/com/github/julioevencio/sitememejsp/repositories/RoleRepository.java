package com.github.julioevencio.sitememejsp.repositories;

import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;

public interface RoleRepository {

	Optional<RoleEntity> findByName(String name);

}
