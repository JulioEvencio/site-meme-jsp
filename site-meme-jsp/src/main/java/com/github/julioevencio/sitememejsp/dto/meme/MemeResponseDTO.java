package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MemeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String image;
	private String tag;
	private String username;
	private String message;
	private Boolean messageSuccess;
	private Boolean messageError;
	private String comment;
	private List<CommentResponseDTO> comments;

	public MemeResponseDTO() {
		this.uuid = null;
		this.image = "";
		this.tag = "";
		this.username = "";
		this.message = "";
		this.messageSuccess = false;
		this.messageError = false;
		this.comment = "";
		this.comments = new ArrayList<>();
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<CommentResponseDTO> getComments() {
		return comments;
	}

	public void setComments(List<CommentResponseDTO> comments) {
		this.comments = comments;
	}

}
