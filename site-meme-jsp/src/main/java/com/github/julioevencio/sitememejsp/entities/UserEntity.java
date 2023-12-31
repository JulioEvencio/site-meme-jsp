package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String username;
	private String email;
	private String password;
	private ImageEntity photo;
	private Boolean enabled;
	private List<MemeEntity> memes;
	private List<CommentEntity> comments;
	private List<RoleEntity> roles;

	public UserEntity() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ImageEntity getPhoto() {
		return photo;
	}

	public void setPhoto(ImageEntity photo) {
		this.photo = photo;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<MemeEntity> getMemes() {
		return memes;
	}

	public void setMemes(List<MemeEntity> memes) {
		this.memes = memes;
	}

	public List<CommentEntity> getComments() {
		return comments;
	}

	public void setComments(List<CommentEntity> comments) {
		this.comments = comments;
	}

	public List<RoleEntity> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}

}
