package com.travel.travelagent.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	public Map<String,String> handleValidation(MethodArgumentNotValidException ex){
		
		Map<String,String> errors=new HashMap<>();
		
		ex.getBindingResult().getFieldErrors().forEach(error->{errors.put(error.getField(), error.getDefaultMessage());});;
		return errors;
		
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)

	@ExceptionHandler(TripNotFoundException.class)

	public String handleTripNotFound(TripNotFoundException ex){

	    return ex.getMessage();

	}
}
