package com.github.julioevencio.sitememejsp.dto.auth;

import java.io.Serializable;

import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public class LoginRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String email;
	private final String password;

	public LoginRequestDTO(String email, String password) throws InvalidDataException {
		if (email == null || email.isBlank()) {
			throw new InvalidDataException("E-mail invalid!");
		}

		if (email.length() > 100) {
			throw new InvalidDataException("E-mail cannot have more than 100 characters!");
		}

		if (password == null || password.isBlank()) {
			throw new InvalidDataException("Password invalid!");
		}

		this.email = email;
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
