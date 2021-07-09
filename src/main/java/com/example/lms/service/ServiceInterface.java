package com.example.lms.service;

import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;

import com.example.lms.util.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ServiceInterface<T> {

	public T save(T object);

	public T find(int id);

	public void delete(int id);

	public void log(String method, String interchange, Object object) throws JsonProcessingException;

	default public HttpResponse<T> createHttpResponse(T object, HttpStatus httpStatus) {
		return new HttpResponse<T>(httpStatus, httpStatus.value(), httpStatus.getReasonPhrase(), object);
	}
}
