package com.example.lms.controller;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.lms.util.HttpResponse;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class CustomExceptionHandler {

	private static final String AUTHENTICATION_PACKAGE = "org.springframework.security.authentication";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> general(Exception ex) {

		switch (ex.getClass().getPackageName()) {

		case AUTHENTICATION_PACKAGE:
			return createHttpResponse(FORBIDDEN, ex.getMessage());
		default:
			return createHttpResponse(INTERNAL_SERVER_ERROR, ex.getMessage());
		}
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<HttpResponse> noSuchElement(NoSuchElementException ex) {

		return createHttpResponse(NOT_FOUND, ex.getMessage());
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	public ResponseEntity<HttpResponse> usernameNotFound(UsernameNotFoundException ex) {

		return createHttpResponse(NOT_FOUND, ex.getMessage());
	}

	private ResponseEntity<HttpResponse> createHttpResponse(HttpStatus httpStatus, String message) {

		HttpResponse httpResponse = new HttpResponse(httpStatus, httpStatus.value(), message.toUpperCase());
		return new ResponseEntity<HttpResponse>(httpResponse, httpStatus);
	}
}
