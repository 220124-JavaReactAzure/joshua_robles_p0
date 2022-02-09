package com.revature.course_app.exceptions;

@SuppressWarnings("serial")
public class AuthenticationException extends RuntimeException {
	
	public AuthenticationException(String message) {
		super(message);
	}
}
