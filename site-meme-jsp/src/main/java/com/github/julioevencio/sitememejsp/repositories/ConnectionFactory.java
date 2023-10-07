package com.github.julioevencio.sitememejsp.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.github.julioevencio.sitememejsp.exceptions.DatabaseConnectionFailedException;

public class ConnectionFactory {

	public static Connection getConnection() {
		try {
			String url = "jdbc:postgresql://localhost:5432/db_site_meme_jsp";
			String user = "postgres";
			String password = "123";

			Class.forName("org.postgresql.Driver");

			return DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			throw new DatabaseConnectionFailedException(e.getMessage());
		}
	}

}
