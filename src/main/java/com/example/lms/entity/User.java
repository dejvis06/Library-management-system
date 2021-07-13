package com.example.lms.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Entity
@Table(name = "user")
@Document(collection = "library management system")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Field(name = "_id")
	private Integer id;

	private String username;

	private String password;

	@Field(name = "active")
	private boolean isActive;

	@Transient
	private String[] authorities;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user"), inverseJoinColumns = @JoinColumn(name = "role"))
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

}
