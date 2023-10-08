package com.github.julioevencio.sitememejsp.dto;

import java.io.Serializable;

import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public class RegisterRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String username;
	private final String email;
	private final String password;

	public RegisterRequestDTO(String username, String email, String password, String passwordConfirm) throws InvalidDataException {
		if (username == null || username.isBlank()) {
			throw new InvalidDataException("Username invalid!");
		}

		if (username.length() > 20) {
			throw new InvalidDataException("Username cannot have more than 20 characters!");
		}

		if (email == null || email.isBlank()) {
			throw new InvalidDataException("E-mail invalid!");
		}

		if (email.length() > 100) {
			throw new InvalidDataException("E-mail cannot have more than 100 characters!");
		}

		if (password == null || password.isBlank()) {
			throw new InvalidDataException("Password invalid!");
		}

		if (!password.equals(passwordConfirm)) {
			throw new InvalidDataException("The passwords are different!");
		}

		this.username = username;
		this.email = email;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

}
