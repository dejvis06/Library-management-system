package com.example.lms.security;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.lms.util.HttpResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	@SuppressWarnings("rawtypes")
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		HttpResponse httpResponse = new HttpResponse(UNAUTHORIZED, UNAUTHORIZED.value(),
				UNAUTHORIZED.getReasonPhrase());

		OutputStream outputStream = response.getOutputStream();

		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(outputStream, httpResponse);

		outputStream.flush();
	}

}
