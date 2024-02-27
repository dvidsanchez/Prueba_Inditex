package com.prueba.bcnc.inditex.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class ControllerPruebaInditexExceptionHandler {

	@ExceptionHandler(PruebaInditexException.class)
	public ResponseEntity<ErrorMessage> pruebaInditexException(PruebaInditexException ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(ex.getHttpStatus().value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, ex.getHttpStatus());
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NoResourceFoundException.class)
	public ResponseEntity<ErrorMessage> noResourceExceptionHandler(Exception ex, WebRequest request) {
		ErrorMessage message = new ErrorMessage(HttpStatus.NOT_FOUND.value(), new Date(), ex.getMessage(),
				request.getDescription(false));

		return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
	}

}
