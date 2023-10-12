package com.github.julioevencio.sitememejsp.controllers.meme;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostMemeRequestDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostMemeResponseDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.services.MemeService;
import com.github.julioevencio.sitememejsp.services.MemeServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class PostMemeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final MemeService memeService;

	public PostMemeController() {
		this.memeService = new MemeServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostMemeResponseDTO postMemeResponseDTO = new PostMemeResponseDTO();

		postMemeResponseDTO.setTags(memeService.findAllTags());

		request.setAttribute("postMemeResponseDTO", postMemeResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/meme/post.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PostMemeResponseDTO postMemeResponseDTO = new PostMemeResponseDTO();
		postMemeResponseDTO.setTags(memeService.findAllTags());

		try {
			String tag = request.getParameter("tag");
			Part part = request.getPart("image");
			byte[] image = IOUtils.toByteArray(part.getInputStream());
			String typeImage = part.getContentType();
			UserSessionDTO userSessionDTO = (UserSessionDTO) request.getSession().getAttribute("userSessionDTO");

			PostMemeRequestDTO dto = new PostMemeRequestDTO(image, typeImage, tag, userSessionDTO);

			memeService.save(dto);

			postMemeResponseDTO.setMessageSuccess(true);
			postMemeResponseDTO.setMessage("Meme created successfully!");
		} catch (InvalidDataException e) {
			postMemeResponseDTO.setMessageError(true);
			postMemeResponseDTO.setMessage(e.getMessage());
		}

		request.setAttribute("postMemeResponseDTO", postMemeResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/meme/post.jsp");
		dispatcher.forward(request, response);
	}

}
