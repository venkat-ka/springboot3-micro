package com.v2micro.v2microtwo.ExceptionHandler;


import java.util.Date;

import org.aspectj.lang.annotation.Aspect;
import org.modelmapper.spi.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

// If we not mentioned @ControllerAdvice while error blank screen with 403 forbidden state only show on exception
@ControllerAdvice
public class ExceptionController {

	// CustomException CustomerAlreadyExistsException.class
	@ExceptionHandler(value=CustomerAlreadyExistsException.class)
	@ResponseStatus(HttpStatus.CONFLICT) 
	public ResponseEntity<ErrorResponse> handleCustomerAlreadyExistsException(CustomerAlreadyExistsException alExcp, WebRequest req) {
		ErrorResponse getMesg =  new ErrorResponse(HttpStatus.CONFLICT.value(), new Date(), alExcp.getMessage(), req.getDescription(false));
		return new ResponseEntity<ErrorResponse>(getMesg, HttpStatus.CONFLICT);
	}
	
	
	// CustomException Exception.class
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest wrq){
		ErrorResponse errRes = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(), wrq.getDescription(false));
	return new ResponseEntity<ErrorResponse>(errRes, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
