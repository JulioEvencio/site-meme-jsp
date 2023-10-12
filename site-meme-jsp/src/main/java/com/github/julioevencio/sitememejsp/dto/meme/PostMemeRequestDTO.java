package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public class PostMemeRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final byte[] image;
	private final String typeImage;
	private final String tag;
	private final UserSessionDTO userSessionDTO;

	public PostMemeRequestDTO(byte[] image, String typeImage, String tag, UserSessionDTO userSessionDTO) throws InvalidDataException {
		if (image == null || typeImage == null || typeImage.isBlank()) {
			throw new InvalidDataException("Invalid image!");
		}

		if (tag == null || tag.isBlank()) {
			throw new InvalidDataException("Invalid tag!");
		}

		this.image = image;
		this.typeImage = typeImage;
		this.tag = tag;
		this.userSessionDTO = userSessionDTO;
	}

	public byte[] getImage() {
		return image;
	}

	public String getTypeImage() {
		return typeImage;
	}

	public String getTag() {
		return tag;
	}

	public UserSessionDTO getUserSessionDTO() {
		return userSessionDTO;
	}

}
