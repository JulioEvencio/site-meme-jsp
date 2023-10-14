package com.github.julioevencio.sitememejsp.controllers.user;

import java.io.IOException;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.dto.profile.ProfileResponseDTO;
import com.github.julioevencio.sitememejsp.services.ProfileService;
import com.github.julioevencio.sitememejsp.services.ProfileServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProfileService profileService;

	public ProfileController() {
		this.profileService = new ProfileServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserSessionDTO userSessionDTO = (UserSessionDTO) request.getSession().getAttribute("userSessionDTO");

		ProfileResponseDTO profileResponseDTO = profileService.findByUserUuid(userSessionDTO.getUuid());

		request.setAttribute("profileResponseDTO", profileResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/profile.jsp");
		dispatcher.forward(request, response);
	}

}
