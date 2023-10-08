package com.github.julioevencio.sitememejsp.filters;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.github.julioevencio.sitememejsp.entities.RoleEntity;
import com.github.julioevencio.sitememejsp.entities.UserEntity;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterAuthentication implements Filter {

	private final List<String> URLS_PUBLIC;
	private final List<String> URLS_ROLE_USER;
	private final List<String> URLS_ROLE_ADMIN;

	public FilterAuthentication() {
		URLS_PUBLIC = Arrays.asList("/index.jsp", "/auth/login", "/auth/register");
		URLS_ROLE_USER = Arrays.asList("/profile", "/auth/logout");
		URLS_ROLE_ADMIN = Arrays.asList("/admin");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		String url = request.getServletPath();

		if (url.length() > 7 && url.substring(0, 8).equals("/public/")) {
			chain.doFilter(req, res);
		} else if (URLS_PUBLIC.contains(url)) {
			chain.doFilter(req, res);
		} else if (URLS_ROLE_USER.contains(url)) {
			UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");

			if (userEntity != null) {
				boolean access = false;

				for (RoleEntity roleEntity : userEntity.getRoles()) {
					if (roleEntity.getName().equals("ROLE_USER")) {
						access = true;
						break;
					}
				}

				if (access) {
					chain.doFilter(req, res);
					return;
				}
			}

			HttpServletResponse response = (HttpServletResponse) res;
			response.sendRedirect(request.getContextPath() + "/auth/login");
		} else if (URLS_ROLE_ADMIN.contains(url)) {
			UserEntity userEntity = (UserEntity) request.getSession().getAttribute("user");

			if (userEntity != null) {
				boolean access = false;

				for (RoleEntity roleEntity : userEntity.getRoles()) {
					if (roleEntity.getName().equals("ROLE_ADMIN")) {
						access = true;
						break;
					}
				}

				if (access) {
					chain.doFilter(req, res);
					return;
				}
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("not-found.jsp");
			dispatcher.forward(req, res);
		} else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("not-found.jsp");
			dispatcher.forward(req, res);
		}
	}

}
