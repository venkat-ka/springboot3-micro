package com.v2micro.ExceptionHandler;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
		
	}
	
}
