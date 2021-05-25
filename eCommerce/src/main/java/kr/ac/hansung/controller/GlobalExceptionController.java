package kr.ac.hansung.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.ac.hansung.exception.ErrorResponse;
import kr.ac.hansung.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionController {

	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> 
		handleNotFoundException(HttpServletRequest req, NotFoundException ex) {
		
		String requestURL = req.getRequestURL().toString();
		
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setRequestURL(requestURL);
		errorResponse.setErrorCode("id.notfound.exception");
		errorResponse.setErrorMsg("id " + ex.getId() + " not found");
		
		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
		
	}
	
}
