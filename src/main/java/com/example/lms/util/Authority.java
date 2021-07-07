package com.example.lms.util;

public class Authority {

	public static final String[] USER = { "book:read" };
	public static final String[] SECRETARY = { "book:create", "book:delete" };
	public static final String[] ADMIN = { "user:read", "user:update", "user:delete" };

}
