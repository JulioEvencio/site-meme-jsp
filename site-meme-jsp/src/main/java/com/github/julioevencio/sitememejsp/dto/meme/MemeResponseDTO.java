package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;
import java.util.UUID;

public class MemeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String image;
	private String tag;
	private String username;

	public MemeResponseDTO() {
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

}
