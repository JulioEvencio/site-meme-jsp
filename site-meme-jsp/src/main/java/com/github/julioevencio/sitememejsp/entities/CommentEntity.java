package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.UUID;

public class CommentEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String comment;
	private MemeEntity meme;
	private UserEntity user;

	public CommentEntity() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public MemeEntity getMeme() {
		return meme;
	}

	public void setMeme(MemeEntity meme) {
		this.meme = meme;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

}
