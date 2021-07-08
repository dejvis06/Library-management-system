package com.example.lms.security;

import static com.example.lms.util.SecurityConstant.AUTHORITIES;
import static com.example.lms.util.SecurityConstant.EXPIRATION_TIME;
import static com.example.lms.util.SecurityConstant.ISSUER;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.lms.util.UserCustody;

@Component
public class JWTTokenProvider {

	@Value("jwt.secret")
	private String secret;

	public String generateJwtToken(UserCustody userCustody) {

		String[] claims = getClaimsFromUser(userCustody);

		return JWT.create().withIssuer(ISSUER).withIssuedAt(new Date()).withSubject(userCustody.getUsername())
				.withArrayClaim(AUTHORITIES, claims)
				.withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.sign(Algorithm.HMAC512(secret.getBytes()));
	}

	public Authentication getAuthentication(String username, List<GrantedAuthority> authorities,
			HttpServletRequest httpRequest) {

		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				username, null, authorities);
		usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));

		return usernamePasswordAuthenticationToken;
	}

	public String getSubject(String token) {

		JWTVerifier jwtVerifier = getJWTVerifier();

		return jwtVerifier.verify(token).getSubject();
	}

	private String[] getClaimsFromUser(UserCustody userCustody) {

		return userCustody.getUser().getAuthorities();
	}

	public List<GrantedAuthority> getAuthorities(String token) {

		String[] claims = getClaimsFromToken(token);
		return Arrays.stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private String[] getClaimsFromToken(String token) {

		return getJWTVerifier().verify(token).getClaim(AUTHORITIES).asArray(String.class);
	}

	public boolean isTokenValid(String username, String token) {

		return !username.isEmpty() && !isTokenExpired(getJWTVerifier(), token);
	}

	private boolean isTokenExpired(JWTVerifier jwtVerifier, String token) {

		Date expiration = jwtVerifier.verify(token).getExpiresAt();
		return expiration.before(new Date());
	}

	private JWTVerifier getJWTVerifier() throws JWTVerificationException {

		Algorithm algorithm = Algorithm.HMAC512(secret);
		JWTVerifier jwtVerifier = JWT.require(algorithm).withIssuer(ISSUER).build();

		return jwtVerifier;
	}
}
