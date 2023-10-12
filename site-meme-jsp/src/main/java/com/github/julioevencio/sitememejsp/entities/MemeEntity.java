package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class MemeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private ImageEntity image;
	private UserEntity user;
	private TagEntity tag;
	private List<CommentEntity> comments;

	public MemeEntity() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public ImageEntity getImage() {
		return image;
	}

	public void setImage(ImageEntity image) {
		this.image = image;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TagEntity getTag() {
		return tag;
	}

	public void setTag(TagEntity tag) {
		this.tag = tag;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}
