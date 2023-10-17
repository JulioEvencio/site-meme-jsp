package com.github.julioevencio.sitememejsp.dto.meme;

import java.io.Serializable;
import java.util.List;

public class ViewAllMemeResponseDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<MemeResponseDTO> memes;

	public ViewAllMemeResponseDTO() {
	}

	public List<MemeResponseDTO> getMemes() {
		return memes;
	}

	public void setMemes(List<MemeResponseDTO> memes) {
		this.memes = memes;
	}

}
