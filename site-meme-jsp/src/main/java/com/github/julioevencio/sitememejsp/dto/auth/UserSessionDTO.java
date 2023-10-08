package com.github.julioevencio.sitememejsp.dto.auth;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class UserSessionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final UUID uuid;
	private final String username;
	private final List<String> roles;

	public UserSessionDTO(UUID uuid, String username, List<String> roles) {
		this.uuid = uuid;
		this.username = username;
		this.roles = roles;
	}

	public UUID getUuid() {
		return uuid;
	}

	public String getUsername() {
		return username;
	}

	public List<String> getRoles() {
		return roles;
	}

}
