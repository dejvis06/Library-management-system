package com.example.lms.security;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.example.lms.util.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@SuppressWarnings("rawtypes")
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

	private static final Logger logger = LogManager.getLogger(JwtAccessDeniedHandler.class.getSimpleName());

	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			AccessDeniedException accessDeniedException) throws IOException, ServletException {

		HttpResponse httpResponse = new HttpResponse(UNAUTHORIZED, UNAUTHORIZED.value(),
				UNAUTHORIZED.getReasonPhrase());

		log(httpResponse);

		OutputStream outputStream = response.getOutputStream();

		new ObjectMapper().writeValue(outputStream, httpResponse);

		outputStream.flush();
	}

	private void log(HttpResponse response) throws JsonProcessingException {

		ObjectMapper mapper = new ObjectMapper();
		logger.error(mapper.writeValueAsString(response));
	}

}
