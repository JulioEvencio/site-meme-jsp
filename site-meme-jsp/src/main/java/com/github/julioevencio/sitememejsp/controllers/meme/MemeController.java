package com.github.julioevencio.sitememejsp.controllers.meme;

import java.io.IOException;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
import com.github.julioevencio.sitememejsp.dto.meme.PostCommentRequestDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.services.MemeService;
import com.github.julioevencio.sitememejsp.services.MemeServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MemeController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final MemeService memeService;

	public MemeController() {
		this.memeService = new MemeServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			UUID uuid = UUID.fromString(request.getParameter("uuid"));

			MemeResponseDTO memeResponseDTO = memeService.findMemeByUuid(uuid);

			request.setAttribute("memeResponseDTO", memeResponseDTO);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/meme/meme.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/not-found.jsp");
			dispatcher.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserSessionDTO userSessionDTO = (UserSessionDTO) request.getSession().getAttribute("userSessionDTO");

		if (userSessionDTO == null) {
			response.sendRedirect(request.getContextPath() + "/auth/login");
			return;
		}

		UUID uuid = UUID.fromString(request.getParameter("uuid"));
		String comment = request.getParameter("comment");
		MemeResponseDTO memeResponseDTO = null;

		try {
			PostCommentRequestDTO dto = new PostCommentRequestDTO(comment, uuid, userSessionDTO);

			memeService.save(dto);
			
			memeResponseDTO = memeService.findMemeByUuid(uuid);

			memeResponseDTO.setMessage("Comment added successfully");
			memeResponseDTO.setMessageSuccess(true);
		} catch (InvalidDataException e) {
			memeResponseDTO = memeService.findMemeByUuid(uuid);

			memeResponseDTO.setComment(comment);
			memeResponseDTO.setMessage(e.getMessage());
			memeResponseDTO.setMessageError(true);
		}

		request.setAttribute("memeResponseDTO", memeResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/meme/meme.jsp");
		dispatcher.forward(request, response);
	}
	
}
