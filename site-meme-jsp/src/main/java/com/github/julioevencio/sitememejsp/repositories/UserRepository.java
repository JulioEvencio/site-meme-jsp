package com.github.julioevencio.sitememejsp.repositories;

import java.util.Optional;

import com.github.julioevencio.sitememejsp.entities.UserEntity;

public interface UserRepository {

	Optional<UserEntity> findByUsername(String username);

	Optional<UserEntity> findByEmail(String email);

	UserEntity save(UserEntity userEntity);

}
