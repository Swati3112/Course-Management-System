package com.app.CourseManagement.exception;

import org.springframework.http.HttpStatus;

public class ExceptionOccured  extends RuntimeException{
	public HttpStatus status;

	public ExceptionOccured(String message, HttpStatus status) {
		super(message);
		// TODO Auto-generated constructor stub
		this.status= status;
	}
	
	

}
