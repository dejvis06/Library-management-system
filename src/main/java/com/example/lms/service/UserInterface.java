package com.example.lms.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.lms.entity.User;

public interface UserInterface extends ServiceInterface<User>, UserDetailsService {

	public UserDetails findByUsername(String username);

	public BCryptPasswordEncoder getbCryptPasswordEncoder();
}
