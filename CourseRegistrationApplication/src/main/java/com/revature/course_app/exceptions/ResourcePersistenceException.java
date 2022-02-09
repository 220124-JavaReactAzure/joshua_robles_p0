package com.revature.course_app.exceptions;

@SuppressWarnings("serial")
public class ResourcePersistenceException extends RuntimeException{
	public ResourcePersistenceException(String message) {
		super(message);
	}
}
