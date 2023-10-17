package com.github.julioevencio.sitememejsp.services;

import java.util.List;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostMemeRequestDTO;
import com.github.julioevencio.sitememejsp.dto.meme.ViewAllMemeResponseDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public interface MemeService {

	List<String> findAllTags();

	ViewAllMemeResponseDTO findAll();

	MemeResponseDTO findMemeByUuid(UUID uuid);

	void save(PostMemeRequestDTO dto) throws InvalidDataException;

}
