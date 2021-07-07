package com.example.lms.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.lms.entity.Role;
import com.example.lms.entity.User;
import com.example.lms.repository.UserRepository;
import com.example.lms.util.UserCustody;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public User save(User user) throws Exception {

		user.setPassword(encodePassword(user.getPassword()));
		user.setActive(true);
		// user.setRoles(Arrays.asList(new
		// Role(com.example.lms.util.Role.ROLE_USER.name())));

		return userRepository.save(user);
	}

	public void delete(User user) throws Exception {
		userRepository.delete(user);
	}

	public User find(int id) throws Exception {

		User user = userRepository.findById(id).get();
		user.setAuthorities(getAuthorities(user.getRoles()));

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);
		user.setAuthorities(getAuthorities(user.getRoles()));

		return new UserCustody(user);
	}

	private String[] getAuthorities(List<Role> roles) {

		List<String> authorities = new ArrayList<>();

		roles.stream().forEach(role -> {

			for (String authority : com.example.lms.util.Role.valueOf(role.getName()).getAuthorities()) {
				authorities.add(authority);
			}
		});
		return StringUtils.toStringArray(authorities);
	}

	public BCryptPasswordEncoder getbCryptPasswordEncoder() {
		return bCryptPasswordEncoder;
	}

	private String encodePassword(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
}
