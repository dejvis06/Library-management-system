package com.example.lms.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.lms.entity.User;

public interface UserInterface extends ServiceInterface<User> {

	public UserDetails findByUsername(String username);
}
