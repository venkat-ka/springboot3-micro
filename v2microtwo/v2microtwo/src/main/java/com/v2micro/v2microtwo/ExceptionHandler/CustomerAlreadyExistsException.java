package com.v2micro.v2microtwo.ExceptionHandler;

public class CustomerAlreadyExistsException extends RuntimeException{
	
	public CustomerAlreadyExistsException(String message) {
		super(message);
		
	}
	
}
