package com.github.julioevencio.sitememejsp.controllers.user;

import java.io.IOException;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;

import com.github.julioevencio.sitememejsp.dto.auth.UserSessionDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileRequestDTO;
import com.github.julioevencio.sitememejsp.dto.profile.UpdateProfileResponseDTO;
import com.github.julioevencio.sitememejsp.exceptions.InvalidDataException;
import com.github.julioevencio.sitememejsp.services.ProfileService;
import com.github.julioevencio.sitememejsp.services.ProfileServiceImpl;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class UpdateProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final ProfileService profileService;

	public UpdateProfileController() {
		this.profileService = new ProfileServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserSessionDTO userSessionDTO = (UserSessionDTO) request.getSession().getAttribute("userSessionDTO");
		UpdateProfileResponseDTO updateProfileResponseDTO = profileService.findByUserUuidForUpdate(userSessionDTO.getUuid());

		request.setAttribute("updateProfileResponseDTO", updateProfileResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/update.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UpdateProfileResponseDTO updateProfileResponseDTO = new UpdateProfileResponseDTO();

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");

		try {
			Part part = request.getPart("photo");
			byte[] image = IOUtils.toByteArray(part.getInputStream());
			String typeImage = part.getContentType();
			UserSessionDTO userSessionDTO = (UserSessionDTO) request.getSession().getAttribute("userSessionDTO");

			UpdateProfileRequestDTO dto = new UpdateProfileRequestDTO(image, typeImage, username, email, password, passwordConfirm, userSessionDTO);

			profileService.update(dto);

			updateProfileResponseDTO.setMessage("Profile Updated Successfully!");
			updateProfileResponseDTO.setMessageSuccess(true);
		} catch (InvalidDataException e) {
			updateProfileResponseDTO.setUsername(username);
			updateProfileResponseDTO.setEmail(email);
			updateProfileResponseDTO.setMessage(e.getMessage());
			updateProfileResponseDTO.setMessageError(true);
		}

		request.setAttribute("updateProfileResponseDTO", updateProfileResponseDTO);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/user/update.jsp");
		dispatcher.forward(request, response);
	}

}
