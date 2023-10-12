package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostMemeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	private Boolean messageSuccess;
	private Boolean messageError;
	private List<String> tags;

	public PostMemeResponseDTO() {
		this.message = "";
		this.messageSuccess = false;
		this.messageError = false;
		this.tags = new ArrayList<>();
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

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

}
