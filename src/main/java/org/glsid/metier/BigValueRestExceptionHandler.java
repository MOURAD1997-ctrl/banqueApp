package org.glsid.metier;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BigValueRestExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<BigValueResponse> handleException(BigValueException exc){
		
		// create a studentErrorResponse
		BigValueResponse error = new BigValueResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exc.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		
		// return ResponseEntity
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
		
	}
	
}
