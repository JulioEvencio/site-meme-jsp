package com.github.julioevencio.sitememejsp.services;

import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.profile.ProfileResponseDTO;

public interface ProfileService {

	ProfileResponseDTO findByUserUuid(UUID uuid);

}
