package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class MemeEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String image;
	private UserEntity user;
	private TagEntity tags;
	private List<CommentEntity> comments;

	public MemeEntity() {
	}

	public MemeEntity(UUID uuid, String image, UserEntity user, TagEntity tags, List<CommentEntity> comments) {
		this.uuid = uuid;
		this.image = image;
		this.user = user;
		this.tags = tags;
		this.comments = comments;
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public TagEntity getTags() {
		return tags;
	}

	public void setTags(TagEntity tags) {
		this.tags = tags;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

}
