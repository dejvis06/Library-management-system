package com.example.lms.controller;

import static org.springframework.http.HttpStatus.*;

import java.util.NoSuchElementException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.lms.util.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
@SuppressWarnings("rawtypes")
public class CustomExceptionHandler {

	private static final String AUTHENTICATION_PACKAGE = "org.springframework.security.authentication";
	private static final Logger logger = LogManager.getLogger(CustomExceptionHandler.class.getSimpleName());

	@ExceptionHandler(Exception.class)
	public ResponseEntity<HttpResponse> general(Exception ex) {

		ex.printStackTrace();
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
		try {
			log(httpResponse);
		} catch (JsonProcessingException e) {
			return new ResponseEntity<HttpResponse>(new HttpResponse(INTERNAL_SERVER_ERROR,
					INTERNAL_SERVER_ERROR.value(), INTERNAL_SERVER_ERROR.getReasonPhrase()), INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<HttpResponse>(httpResponse, httpStatus);
	}

	private void log(HttpResponse response) throws JsonProcessingException {
		logger.error(new ObjectMapper().writeValueAsString(response));
	}
}
