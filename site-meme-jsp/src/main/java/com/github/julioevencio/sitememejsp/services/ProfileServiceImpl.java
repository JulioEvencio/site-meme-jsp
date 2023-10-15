package com.github.julioevencio.sitememejsp.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;
import org.mindrot.jbcrypt.BCrypt;

import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
import com.github.julioevencio.sitememejsp.dto.profile.ProfileResponseDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileRequestDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileResponseDTO;
import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.exceptions.UpdateFailedException;
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

			ImageEntity userPhoto = imageRepository.findByUuid(connection, userEntity.getPhoto().getUuid()).orElseThrow(() -> new RuntimeException());
			profileResponseDTO.setPhoto(userPhoto.getImageBase64());

			List<MemeResponseDTO> memes = new ArrayList<>();

			for (MemeEntity memeEntity : memesEntities) {
				ImageEntity imageEntity = imageRepository.findByUuid(connection, memeEntity.getImage().getUuid()).orElseThrow(() -> new RuntimeException());
				MemeResponseDTO memeResponseDTO = new MemeResponseDTO();

				memeResponseDTO.setUuid(memeEntity.getUuid());
				memeResponseDTO.setImage(imageEntity.getImageBase64());

				memes.add(memeResponseDTO);
			}

			profileResponseDTO.setMemes(memes);

			return profileResponseDTO;
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public UpdateProfileResponseDTO findByUserUuidForUpdate(UUID uuid) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			UserEntity userEntity = userRepository.findByUuid(connection, uuid).orElseThrow(() -> new RuntimeException());
			UpdateProfileResponseDTO updateProfileResponseDTO = new UpdateProfileResponseDTO();

			updateProfileResponseDTO.setUsername(userEntity.getUsername());
			updateProfileResponseDTO.setEmail(userEntity.getEmail());

			return updateProfileResponseDTO;
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void update(UpdateProfileRequestDTO dto) throws InvalidDataException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			try {
				connection.setAutoCommit(false);

				UserEntity userEntity = userRepository.findByUuid(connection, dto.getUserSessionDTO().getUuid()).orElseThrow(() -> new RuntimeException());
				ImageEntity imageEntity = imageRepository.findByUuid(connection, userEntity.getPhoto().getUuid()).orElseThrow(() -> new RuntimeException());

				if (!userEntity.getUsername().equals(dto.getUsername()) && userRepository.findByUsername(connection, dto.getUsername()).isPresent()) {
					throw new InvalidDataException("Username already!");
				}

				if (!userEntity.getEmail().equals(dto.getEmail()) && userRepository.findByEmail(connection, dto.getEmail()).isPresent()) {
					throw new InvalidDataException("E-mail already!");
				}

				userEntity.setUsername(dto.getUsername());
				userEntity.setEmail(dto.getEmail());
				userEntity.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));

				String imageBase64 = "data:" + dto.getTypeImage() + ";base64," + Base64.encodeBase64String(dto.getImage());

				imageEntity.setImageBase64(imageBase64);
				imageEntity.setType(dto.getTypeImage());

				userRepository.update(connection, userEntity);
				imageRepository.update(connection, imageEntity);

				connection.commit();
			} catch (SQLException | FindFailedException | UpdateFailedException e) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					throw new RuntimeException();
				}

				throw new RuntimeException();
			}
		} catch (SQLException | DatabaseConnectionFailedException databaseConnectionFailedException) {
			throw new RuntimeException();
		}
	}

}
