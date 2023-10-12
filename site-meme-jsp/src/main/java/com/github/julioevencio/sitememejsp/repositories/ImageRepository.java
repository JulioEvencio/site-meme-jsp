package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;

import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;

public interface ImageRepository {

	ImageEntity save(Connection connection, ImageEntity imageEntity) throws CreateFailedException;

}
