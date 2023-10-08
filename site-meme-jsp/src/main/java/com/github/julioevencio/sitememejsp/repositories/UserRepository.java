package com.github.julioevencio.sitememejsp.repositories;

import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface UserRepository {

	Optional<UserEntity> findByUsername(String username) throws DatabaseConnectionFailedException, FindFailedException;

	Optional<UserEntity> findByEmail(String email) throws DatabaseConnectionFailedException, FindFailedException;

	void save(UserEntity userEntity) throws DatabaseConnectionFailedException, CreateFailedException;

	void addRole(UserEntity userEntity, RoleEntity roleEntity) throws DatabaseConnectionFailedException, CreateFailedException;

}
