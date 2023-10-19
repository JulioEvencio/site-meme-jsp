package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;

public class PostCommentRequestDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String comment;
	private final UUID memeUuid;
	private final UserSessionDTO userSessionDTO;

	public PostCommentRequestDTO(String comment, UUID memeUuid, UserSessionDTO userSessionDTO) throws InvalidDataException {
		if (comment == null || comment.isBlank() || comment.length() > 255) {
			throw new InvalidDataException("Comment cannot have more than 255 characters!");
		}

		this.comment = comment;
		this.memeUuid = memeUuid;
		this.userSessionDTO = userSessionDTO;
	}

	public String getComment() {
		return comment;
	}

	public UUID getMemeUuid() {
		return memeUuid;
	}

	public UserSessionDTO getUserSessionDTO() {
		return userSessionDTO;
	}

}
