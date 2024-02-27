package com.prueba.bcnc.inditex.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PruebaInditexException extends RuntimeException {

	private static final long serialVersionUID = -7847306031966379026L;

	public PruebaInditexException(String message, HttpStatus httpStatus) {
		super(message);
		this.httpStatus = httpStatus;
	}

	private final HttpStatus httpStatus;

}
