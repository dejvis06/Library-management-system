package com.example.lms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.lms.util.HttpResponse;

public interface ControllerInterface<T> {

	public ResponseEntity<HttpResponse<T>> save(T object);

	public ResponseEntity<HttpResponse<T>> find(int id);

	public ResponseEntity<HttpResponse<T>> delete(int id);

	default ResponseEntity<HttpResponse<T>> createHttpResponse(T object, HttpStatus httpStatus) {

		HttpResponse<T> httpResponse = new HttpResponse<T>(httpStatus, httpStatus.value(), httpStatus.getReasonPhrase(),
				object);
		return new ResponseEntity<HttpResponse<T>>(httpResponse, httpStatus);
	}
}
