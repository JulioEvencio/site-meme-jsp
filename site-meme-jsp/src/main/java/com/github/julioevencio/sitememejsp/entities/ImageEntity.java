package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.UUID;

public class ImageEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String imageBase64;
	private String type;

	public ImageEntity() {
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getImageBase64() {
		return imageBase64;
	}

	public void setImageBase64(String imageBase64) {
		this.imageBase64 = imageBase64;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
