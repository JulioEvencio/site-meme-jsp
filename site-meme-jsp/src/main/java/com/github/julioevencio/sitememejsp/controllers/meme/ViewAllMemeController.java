package com.github.julioevencio.sitememejsp.controllers.meme;

import java.io.IOException;

import com.github.julioevencio.sitememejsp.dto.meme.ViewAllMemeResponseDTO;
import com.github.julioevencio.sitememejsp.services.MemeService;
import com.github.julioevencio.sitememejsp.services.MemeServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ViewAllMemeController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final MemeService memeService;
	
	public ViewAllMemeController() {
		this.memeService = new MemeServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ViewAllMemeResponseDTO viewAllMemeResponseDTO = memeService.findAll();
		
		request.setAttribute("viewAllMemeResponseDTO", viewAllMemeResponseDTO);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/meme/all.jsp");
		dispatcher.forward(request, response);
	}

}
