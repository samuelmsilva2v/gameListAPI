package com.example.demo.domain.exceptions;

public class GameAlreadyInLibraryException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GameAlreadyInLibraryException(String message) {
		super(message);
	}
}
