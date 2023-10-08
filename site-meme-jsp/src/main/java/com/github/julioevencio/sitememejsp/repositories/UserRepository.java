package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;

public interface UserRepository {

	Optional<UserEntity> findByUsername(Connection connection, String username) throws FindFailedException;

	Optional<UserEntity> findByEmail(Connection connection, String email) throws FindFailedException;

	void save(Connection connection, UserEntity userEntity) throws CreateFailedException;

	void addRole(Connection connection, UserEntity userEntity, RoleEntity roleEntity) throws CreateFailedException;

}
