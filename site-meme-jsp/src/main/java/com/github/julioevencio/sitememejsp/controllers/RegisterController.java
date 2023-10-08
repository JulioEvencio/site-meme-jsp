package com.github.julioevencio.sitememejsp.controllers;

import java.io.IOException;

import com.github.julioevencio.sitememejsp.dto.RegisterRequestDTO;
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
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/register.jsp");

		request.setAttribute("messageSuccess", false);
		request.setAttribute("messageError", false);

		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("messageSuccess", false);
		request.setAttribute("messageError", false);

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		try {
			RegisterRequestDTO dto = new RegisterRequestDTO(username, email, password, passwordConfirm);

			userService.register(dto);

			request.setAttribute("messageSuccess", true);
			request.setAttribute("message", "Account created successfully!");
		} catch (InvalidDataException e) {
			request.setAttribute("email", email);
			request.setAttribute("username", username);
			
			request.setAttribute("messageError", true);
			request.setAttribute("message", e.getMessage());
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/register.jsp");
		dispatcher.forward(request, response);
	}

}
