package com.github.julioevencio.sitememejsp.repositories;

import java.util.List;

import com.github.julioevencio.sitememejsp.entities.CommentEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;

public interface CommentRepository {

	CommentEntity save(CommentEntity commentEntity);

	List<CommentEntity> findAllByMeme(MemeEntity memeEntity);

}
