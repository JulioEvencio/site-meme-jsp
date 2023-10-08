package com.github.julioevencio.sitememejsp.services;

import com.github.julioevencio.sitememejsp.dto.RegisterRequestDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public interface UserService {

	void register(RegisterRequestDTO dto) throws InvalidDataException;

}
