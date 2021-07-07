package com.example.lms.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.lms.util.SecurityConstant;

@Component
public class JwtAuthorizationFilter extends OncePerRequestFilter {

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String authorizationheader = request.getHeader(HttpHeaders.AUTHORIZATION);

		if (authorizationheader == null) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = authorizationheader.substring(SecurityConstant.TOKEN_PREFIX.length());
		String username = jwtTokenProvider.getSubject(token);

		if (jwtTokenProvider.isTokenValid(username, token)
				&& SecurityContextHolder.getContext().getAuthentication() == null) {

			List<GrantedAuthority> authorities = jwtTokenProvider.getAuthorities(token);

			Authentication authentication = jwtTokenProvider.getAuthentication(username, authorities, request);
			SecurityContextHolder.getContext().setAuthentication(authentication);

		} else if (!jwtTokenProvider.isTokenValid(username, token)) {
			SecurityContextHolder.clearContext();
		}

		filterChain.doFilter(request, response);
	}

}
