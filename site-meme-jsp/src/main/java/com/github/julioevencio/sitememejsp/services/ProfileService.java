package com.github.julioevencio.sitememejsp.services;

import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.profile.ProfileResponseDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileRequestDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileResponseDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public interface ProfileService {

	ProfileResponseDTO findByUserUuid(UUID uuid);

	UpdateProfileResponseDTO findByUserUuidForUpdate(UUID uuid);

	void update(UpdateProfileRequestDTO dto) throws InvalidDataException;

}
