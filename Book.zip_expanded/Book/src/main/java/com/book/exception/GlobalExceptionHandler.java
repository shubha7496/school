package com.book.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.book.response.APIResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(BooKIdNotFoundException.class)
	public APIResponse handleBooKIdNotFoundException(BooKIdNotFoundException ex) {
  	  APIResponse apiResponse =new APIResponse(HttpStatus.NOT_FOUND,"book id not found", ex.getMessage());

		return apiResponse;
	}
//	
//	@ExceptionHandler(OtpNotFoundException.class)
//	public ProblemDetail handleOtpNotFoundException(OtpNotFoundException ex) {
//		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//	}
//	
//	@ExceptionHandler(UserNotFoundException.class)
//	public ProblemDetail handleUserNotFoundException(UserNotFoundException ex) {
//		return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
//	}


}
