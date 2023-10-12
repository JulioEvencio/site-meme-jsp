package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;

import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;

public interface MemeRepository {

	void save(Connection connection, MemeEntity memeEntity) throws CreateFailedException;

}
