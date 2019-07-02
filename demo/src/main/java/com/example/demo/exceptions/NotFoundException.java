package com.example.demo.exceptions;

@SuppressWarnings("serial")
public class NotFoundException extends Exception {

	public NotFoundException() {
		super("Not found");
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(Throwable cause) {
		super("Not found", cause);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
