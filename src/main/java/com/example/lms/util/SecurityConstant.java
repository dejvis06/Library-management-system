package com.example.lms.util;

public class SecurityConstant {

	public static final long EXPIRATION_TIME = 432_000_000; // 5 days in milliseconds

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String JWT_TOKEN_HEADER = "Jwt-Token";

	public static final String TOKEN_CANNOT_BE_VERIFIED = "Token cannot be verified";

	public static final String ISSUER = "Issuer";

	public static final String AUTHORITIES = "Authorities";

	public static final String[] PUBLIC_URLS = { "/user/login", "/user/save","/user/delete" };
}
