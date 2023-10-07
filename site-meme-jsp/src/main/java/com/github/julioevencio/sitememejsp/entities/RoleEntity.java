package com.github.julioevencio.sitememejsp.entities;

import java.io.Serializable;
import java.util.UUID;

public class RoleEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private UUID uuid;
	private String name;

	public RoleEntity() {
	}

	public RoleEntity(UUID uuid, String name) {
		this.uuid = uuid;
		this.name = name;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
