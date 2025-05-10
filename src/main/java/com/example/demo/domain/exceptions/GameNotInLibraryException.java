package com.example.demo.domain.exceptions;

public class GameNotInLibraryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameNotInLibraryException(String message) {
		super(message);
	}
}
