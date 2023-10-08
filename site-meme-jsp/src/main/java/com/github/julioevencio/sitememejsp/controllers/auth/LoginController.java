package com.github.julioevencio.sitememejsp.controllers.auth;

import java.io.IOException;

import com.github.julioevencio.sitememejsp.dto.auth.LoginRequestDTO;
import com.github.julioevencio.sitememejsp.dto.auth.LoginResponseDTO;
import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.services.UserService;
import com.github.julioevencio.sitememejsp.services.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UserService userService;

	public LoginController() {
		userService = new UserServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/auth/login.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try {
			LoginRequestDTO dto = new LoginRequestDTO(email, password);

			UserSessionDTO userSessionDTO = userService.login(dto);

			request.getSession().setAttribute("userSessionDTO", userSessionDTO);

			response.sendRedirect(request.getContextPath() + "/user/profile");
		}catch (InvalidDataException e) {
			LoginResponseDTO loginResponseDTO = new LoginResponseDTO();

			loginResponseDTO.setEmail(email);
			loginResponseDTO.setMessage(e.getMessage());
			loginResponseDTO.setMessageError(true);

			request.setAttribute("loginResponseDTO", loginResponseDTO);

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/auth/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}
