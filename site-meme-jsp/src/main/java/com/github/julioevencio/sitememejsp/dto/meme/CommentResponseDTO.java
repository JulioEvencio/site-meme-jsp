package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;

public class CommentResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String comment;

	public CommentResponseDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
