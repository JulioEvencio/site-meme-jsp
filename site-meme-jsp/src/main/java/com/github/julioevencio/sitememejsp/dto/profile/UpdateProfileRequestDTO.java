package com.github.julioevencio.sitememejsp.dto.profile;

import java.io.Serializable;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public class UpdateProfileRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final byte[] image;
	private final String typeImage;
	private final String username;
	private final String email;
	private final String password;
	private final UserSessionDTO userSessionDTO;

	public UpdateProfileRequestDTO(byte[] image, String typeImage, String username, String email, String password, String passwordConfirm, UserSessionDTO userSessionDTO) throws InvalidDataException {
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

		if (passwordConfirm == null || !password.equals(passwordConfirm)) {
			throw new InvalidDataException("The passwords are different!");
		}

		this.image = image;
		this.typeImage = typeImage;
		this.username = username;
		this.email = email;
		this.password = password;
		this.userSessionDTO = userSessionDTO;
	}

	public byte[] getImage() {
		return image;
	}

	public String getTypeImage() {
		return typeImage;
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

	public UserSessionDTO getUserSessionDTO() {
		return userSessionDTO;
	}

}
