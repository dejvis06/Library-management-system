package com.example.lms.util;

import static java.util.Arrays.stream;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.lms.entity.User;

public class UserCustody implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1189789331569315174L;

	private User user;

	public UserCustody(User user) {
		super();
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if (user.getAuthorities() != null)
			return stream(user.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		else
			return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return user.isActive();
	}

	public User getUser() {
		return user;
	}

}
