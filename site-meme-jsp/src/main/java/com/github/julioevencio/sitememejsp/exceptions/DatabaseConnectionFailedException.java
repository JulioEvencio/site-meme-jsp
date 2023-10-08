package com.github.julioevencio.sitememejsp.exceptions;

public class DatabaseConnectionFailedException extends Exception {

	private static final long serialVersionUID = 1L;

	public DatabaseConnectionFailedException(String message) {
		super(message);
	}

}
