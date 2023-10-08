package com.github.julioevencio.sitememejsp.controllers.auth;

import java.io.IOException;

import com.github.julioevencio.sitememejsp.dto.auth.RegisterRequestDTO;
import com.github.julioevencio.sitememejsp.dto.auth.RegisterResponseDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.services.UserService;
import com.github.julioevencio.sitememejsp.services.UserServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final UserService userService;

	public RegisterController() {
		userService = new UserServiceImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/auth/register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO();

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		try {
			RegisterRequestDTO dto = new RegisterRequestDTO(username, email, password, passwordConfirm);

			userService.register(dto);

			registerResponseDTO.setMessage("Account created successfully!");
			registerResponseDTO.setMessageSuccess(true);
		} catch (InvalidDataException e) {
			registerResponseDTO.setUsername(username);
			registerResponseDTO.setEmail(email);
			registerResponseDTO.setMessage(e.getMessage());
			registerResponseDTO.setMessageError(true);
		}

		request.setAttribute("registerResponseDTO", registerResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/auth/register.jsp");
		dispatcher.forward(request, response);
	}

}
