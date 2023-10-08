package com.github.julioevencio.sitememejsp.dto.auth;

import java.io.Serializable;

public class LoginResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String email;
	private String message;
	private Boolean messageError;

	public LoginResponseDTO() {
		email = "";
		message = "";
		messageError = false;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getMessageError() {
		return messageError;
	}

	public void setMessageError(Boolean messageError) {
		this.messageError = messageError;
	}

}
