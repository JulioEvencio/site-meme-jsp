package com.github.julioevencio.sitememejsp.services;

import java.util.List;

import com.github.julioevencio.sitememejsp.dto.meme.PostMemeRequestDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public interface MemeService {

	List<String> findAllTags();

	void save(PostMemeRequestDTO dto) throws InvalidDataException;

}
