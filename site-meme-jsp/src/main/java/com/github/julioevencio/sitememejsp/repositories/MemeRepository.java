package com.github.julioevencio.sitememejsp.repositories;

import java.util.List;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.TagEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;

public interface MemeRepository {

	MemeEntity findByUuid(UUID uuid);

	MemeEntity save(MemeEntity memeEntity);

	List<MemeEntity> findAll();

	List<MemeEntity> findByUser(UserEntity userEntity);

	List<MemeEntity> findByTag(TagEntity tagEntity);

}
