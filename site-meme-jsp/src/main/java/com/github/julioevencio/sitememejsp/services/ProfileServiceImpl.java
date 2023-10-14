package com.github.julioevencio.sitememejsp.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
import com.github.julioevencio.sitememejsp.dto.profile.ProfileResponseDTO;
import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;
import com.github.julioevencio.sitememejsp.repositories.ConnectionFactory;
import com.github.julioevencio.sitememejsp.repositories.ImageRepository;
import com.github.julioevencio.sitememejsp.repositories.ImageRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.MemeRepository;
import com.github.julioevencio.sitememejsp.repositories.MemeRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.UserRepository;
import com.github.julioevencio.sitememejsp.repositories.UserRepositoryImpl;

public class ProfileServiceImpl implements ProfileService {

	private final UserRepository userRepository;
	private final MemeRepository memeRepository;
	private final ImageRepository imageRepository;

	public ProfileServiceImpl() {
		this.userRepository = new UserRepositoryImpl();
		this.memeRepository = new MemeRepositoryImpl();
		this.imageRepository = new ImageRepositoryImpl();
	}

	@Override
	public ProfileResponseDTO findByUserUuid(UUID uuid) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			UserEntity userEntity = userRepository.findByUuid(connection, uuid).orElseThrow(() -> new RuntimeException());
			List<MemeEntity> memesEntities = memeRepository.findAllByUserUuid(connection, uuid);

			ProfileResponseDTO profileResponseDTO = new ProfileResponseDTO();

			profileResponseDTO.setUsername(userEntity.getUsername());
			// profileResponseDTO.setPhoto(userEntity.getPhoto().getImageBase64());

			List<MemeResponseDTO> memes = new ArrayList<>();

			for (MemeEntity memeEntity : memesEntities) {
				ImageEntity imageEntity = imageRepository.findByUuid(connection, memeEntity.getImage().getUuid()).orElseThrow(() -> new RuntimeException());
				MemeResponseDTO memeResponseDTO = new MemeResponseDTO();

				memeResponseDTO.setUuid(imageEntity.getUuid());
				memeResponseDTO.setImage(imageEntity.getImageBase64());

				memes.add(memeResponseDTO);
			}

			profileResponseDTO.setMemes(memes);

			return profileResponseDTO;
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

}
