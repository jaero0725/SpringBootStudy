package kr.ac.hansung.cse.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import kr.ac.hansung.cse.exception.ErrorResponse;
import kr.ac.hansung.cse.exception.UserDuplicatedException;
import kr.ac.hansung.cse.exception.UserNotFoundException;

//특정 컨트롤러가 아니라, Application 전체에 관해 Exception 처리를 한다. 
@ControllerAdvice
public class GlobalExceptionController {
	
	// Exception Handler
	// 전체 Exception 처리 -> 내부가 아니라, Exception을
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleUserNotFoundException(HttpServletRequest req, UserNotFoundException ex) {

		String requestURL = req.getRequestURL().toString(); // 사용자가 요청한 URL 정보를 출력.

		// ErrorResponse를 만들어서, Http response body에 넣도록 한다.
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setRequestURL(requestURL);
		errorResponse.setErrorCode("user.notfound.exception");
		errorResponse.setErrorMsg("User with id " + ex.getUserid() + " not found");

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(UserDuplicatedException.class)
	public ResponseEntity<ErrorResponse> handleUserDuplicatedException(HttpServletRequest req,
			UserDuplicatedException ex) {

		String requestURL = req.getRequestURL().toString(); // 사용자가 요청한 URL 정보를 출력.

		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setRequestURL(requestURL);
		errorResponse.setErrorCode("user.dulicated.exception");
		errorResponse.setErrorMsg("Unable to create. A user with name " + ex.getUsername());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.CONFLICT);
	}

}
