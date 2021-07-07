package com.example.lms.util;

import org.springframework.http.HttpStatus;

public class HttpResponse<T> {

	private HttpStatus httpStatus;

	private int httpStatusCode;

	private String reason;

	private String message;

	private T object;

	public HttpResponse(HttpStatus httpStatus, int httpStatusCode, String message, T object) {
		super();
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
		this.message = message;
		this.object = object;
	}

	public HttpResponse(HttpStatus httpStatus, int httpStatusCode, String reason) {
		super();
		this.httpStatus = httpStatus;
		this.httpStatusCode = httpStatusCode;
		this.reason = reason;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getObject() {
		return object;
	}

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	public void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	public void setObject(T object) {
		this.object = object;
	}

}