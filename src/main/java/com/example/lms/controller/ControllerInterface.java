package com.example.lms.controller;

import org.springframework.http.ResponseEntity;

import com.example.lms.util.HttpResponse;

public interface ControllerInterface<T> {

	public ResponseEntity<HttpResponse<T>> save(T object) throws Exception;

	public ResponseEntity<HttpResponse<T>> find(int id) throws Exception;

	public ResponseEntity<HttpResponse<T>> delete(int id) throws Exception;
}
