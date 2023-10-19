package com.github.julioevencio.sitememejsp.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tomcat.util.codec.binary.Base64;

import com.github.julioevencio.sitememejsp.dto.meme.CommentResponseDTO;
import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostCommentRequestDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostMemeRequestDTO;
import com.github.julioevencio.sitememejsp.dto.meme.ViewAllMemeResponseDTO;
import com.github.julioevencio.sitememejsp.entities.CommentEntity;
import com.github.julioevencio.sitememejsp.entities.ImageEntity;
import com.github.julioevencio.sitememejsp.entities.MemeEntity;
import com.github.julioevencio.sitememejsp.entities.TagEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;
import com.github.julioevencio.sitememejsp.exceptions.CreateFailedException;
import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;
import com.github.julioevencio.sitememejsp.exceptions.FindFailedException;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.repositories.CommentRepository;
import com.github.julioevencio.sitememejsp.repositories.CommentRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.ConnectionFactory;
import com.github.julioevencio.sitememejsp.repositories.ImageRepository;
import com.github.julioevencio.sitememejsp.repositories.ImageRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.MemeRepository;
import com.github.julioevencio.sitememejsp.repositories.MemeRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.TagRepository;
import com.github.julioevencio.sitememejsp.repositories.TagRepositoryImpl;
import com.github.julioevencio.sitememejsp.repositories.UserRepository;
import com.github.julioevencio.sitememejsp.repositories.UserRepositoryImpl;

public class MemeServiceImpl implements MemeService {

	private final MemeRepository memeRepository;
	private final ImageRepository imageRepository;
	private final UserRepository userRepository;
	private final TagRepository tagRepository;
	private final CommentRepository commentRepository;

	public MemeServiceImpl() {
		this.memeRepository = new MemeRepositoryImpl();
		this.imageRepository = new ImageRepositoryImpl();
		this.userRepository = new UserRepositoryImpl();
		this.tagRepository = new TagRepositoryImpl();
		this.commentRepository = new CommentRepositoryImpl();
	}

	@Override
	public List<String> findAllTags() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			return tagRepository.findAll(connection).stream().map(tag -> tag.getName()).toList();
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public ViewAllMemeResponseDTO findAll() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			ViewAllMemeResponseDTO viewAllMemeResponseDTO = new ViewAllMemeResponseDTO();
			List<MemeEntity> memesEntities = memeRepository.findAll(connection);
			List<MemeResponseDTO> memesResponseDTOs = new ArrayList<>();

			for (MemeEntity meme : memesEntities) {
				MemeResponseDTO memeDTO = new MemeResponseDTO();
				ImageEntity imageEntity = imageRepository.findByUuid(connection, meme.getImage().getUuid()).orElseThrow(() -> new FindFailedException("Meme not found"));

				memeDTO.setUuid(meme.getUuid());
				memeDTO.setImage(imageEntity.getImageBase64());

				memesResponseDTOs.add(memeDTO);
			}

			viewAllMemeResponseDTO.setMemes(memesResponseDTOs);

			return viewAllMemeResponseDTO;
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public MemeResponseDTO findMemeByUuid(UUID uuid) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			MemeEntity memeEntity = memeRepository.findByUuid(connection, uuid).orElseThrow(() -> new RuntimeException());

			ImageEntity imageEntity = imageRepository.findByUuid(connection, memeEntity.getImage().getUuid()).orElseThrow(() -> new RuntimeException());
			UserEntity userEntity = userRepository.findByUuid(connection, memeEntity.getUser().getUuid()).orElseThrow(() -> new RuntimeException());
			TagEntity tagEntity = tagRepository.findByUuid(connection, memeEntity.getTag().getUuid()).orElseThrow(() -> new RuntimeException());

			MemeResponseDTO memeResponseDTO = new MemeResponseDTO();

			memeResponseDTO.setUuid(uuid);
			memeResponseDTO.setImage(imageEntity.getImageBase64());
			memeResponseDTO.setUsername(userEntity.getUsername());
			memeResponseDTO.setTag(tagEntity.getName());

			List<CommentEntity> commentEntities = commentRepository.findByMemeUuid(connection, memeEntity.getUuid());
			List<CommentResponseDTO> comments = new ArrayList<>();

			for (CommentEntity commentEntity : commentEntities) {
				CommentResponseDTO commentResponseDTO = new CommentResponseDTO();
				UserEntity user = userRepository.findByUuid(connection, commentEntity.getUser().getUuid()).orElseThrow(() -> new RuntimeException());

				commentResponseDTO.setUsername(user.getUsername());
				commentResponseDTO.setComment(commentEntity.getComment());

				comments.add(commentResponseDTO);
			}

			memeResponseDTO.setComments(comments);

			return memeResponseDTO;
		} catch (SQLException | DatabaseConnectionFailedException | FindFailedException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void save(PostMemeRequestDTO dto) throws InvalidDataException {
		try (Connection connection = ConnectionFactory.getConnection()) {
			try {
				connection.setAutoCommit(false);

				ImageEntity imageEntity = new ImageEntity();
				String imageBase64 = "data:" + dto.getTypeImage() + ";base64," + Base64.encodeBase64String(dto.getImage());

				imageEntity.setImageBase64(imageBase64);
				imageEntity.setType(dto.getTypeImage());

				imageEntity = imageRepository.save(connection, imageEntity);

				TagEntity tagEntity = tagRepository.findByName(connection, dto.getTag()).orElseThrow(() -> new InvalidDataException("Invalid tag!"));

				UserEntity userEntity = new UserEntity();
				userEntity.setUuid(dto.getUserSessionDTO().getUuid());

				MemeEntity memeEntity = new MemeEntity();

				memeEntity.setImage(imageEntity);
				memeEntity.setTag(tagEntity);
				memeEntity.setUser(userEntity);

				memeRepository.save(connection, memeEntity);

				connection.commit();
			} catch (SQLException | CreateFailedException | FindFailedException e) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					throw new RuntimeException();
				}

				e.printStackTrace();

				throw new RuntimeException();
			}
		} catch (SQLException | DatabaseConnectionFailedException databaseConnectionFailedException) {
			throw new RuntimeException();
		}
	}

	@Override
	public void save(PostCommentRequestDTO dto) {
		try (Connection connection = ConnectionFactory.getConnection()) {
			try {
				connection.setAutoCommit(false);

				CommentEntity commentEntity = new CommentEntity();
				UserEntity userEntity = new UserEntity();
				MemeEntity memeEntity = new MemeEntity();

				commentEntity.setComment(dto.getComment());
				
				userEntity.setUuid(dto.getUserSessionDTO().getUuid());
				commentEntity.setUser(userEntity);

				memeEntity.setUuid(dto.getMemeUuid());
				commentEntity.setMeme(memeEntity);

				commentRepository.save(connection, commentEntity);

				connection.commit();
			} catch (SQLException | CreateFailedException e) {
				try {
					connection.rollback();
				} catch (SQLException rollbackException) {
					throw new RuntimeException();
				}

				e.printStackTrace();

				throw new RuntimeException();
			}
		} catch (SQLException | DatabaseConnectionFailedException databaseConnectionFailedException) {
			throw new RuntimeException();
		}
	}

}
