package com.example.lms.util;

import static com.example.lms.util.Authority.*;

public enum Role {

	ROLE_USER(USER), ROLE_SECRETARY(SECRETARY), ROLE_ADMIN(ADMIN);

	private String[] authorities;

	private Role(String... authorities) {
		this.authorities = authorities;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

}