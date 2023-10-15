package com.github.julioevencio.sitememejsp.dto.profile;

import java.io.Serializable;

public class UpdateProfileResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String email;
	private String message;
	private Boolean messageSuccess;
	private Boolean messageError;

	public UpdateProfileResponseDTO() {
		this.username = "";
		this.email = "";
		this.message = "";
		this.messageSuccess = false;
		this.messageError = false;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public Boolean getMessageSuccess() {
		return messageSuccess;
	}

	public void setMessageSuccess(Boolean messageSuccess) {
		this.messageSuccess = messageSuccess;
	}

	public Boolean getMessageError() {
		return messageError;
	}

	public void setMessageError(Boolean messageError) {
		this.messageError = messageError;
	}

}
