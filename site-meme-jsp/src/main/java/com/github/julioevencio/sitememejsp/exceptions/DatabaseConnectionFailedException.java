package com.github.julioevencio.sitememejsp.exceptions;

public class DatabaseConnectionFailedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DatabaseConnectionFailedException(String message) {
		super(message);
	}

}
