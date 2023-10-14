package com.github.julioevencio.sitememejsp.dto.profile;

import java.io.Serializable;
import java.util.List;

import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;

public class ProfileResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String username;
	private String photo;
	private List<MemeResponseDTO> memes;

	public ProfileResponseDTO() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public List<MemeResponseDTO> getMemes() {
		return memes;
	}

	public void setMemes(List<MemeResponseDTO> memes) {
		this.memes = memes;
	}

}
