package com.example.lms.entity;

import java.util.List;

import javax.persistence.MappedSuperclass;

import com.example.lms.entity.mongo.Role;

@MappedSuperclass
public abstract class User {

	private String username;

	private String password;

	private String[] authorities;

	private boolean isActive;

	private List<Role> roles;

	public User() {

	}

	public User(String username) {
		super();
		this.username = username;
	}

	public User(String username, String password, boolean isActive) {
		super();
		this.username = username;
		this.password = password;
		this.isActive = isActive;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String[] getAuthorities() {
		return authorities;
	}

	public void setAuthorities(String[] authorities) {
		this.authorities = authorities;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
