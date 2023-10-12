package com.github.julioevencio.sitememejsp.controllers.meme;

import java.io.IOException;
import java.util.UUID;

import com.github.julioevencio.sitememejsp.dto.meme.MemeResponseDTO;
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

}
