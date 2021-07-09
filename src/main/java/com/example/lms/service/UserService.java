package com.example.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.lms.entity.Role;
import com.example.lms.entity.User;
import com.example.lms.repository.jpa.UserRepository;
import com.example.lms.util.UserCustody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile("mysql")
public class UserService implements UserDetailsService, ServiceInterface<User> {

	private static final String USERNAME_NOT_FOUND = "Username not found!";

	private static final Logger logger = LogManager.getLogger(UserService.class.getSimpleName());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User save(User user) {

		if (user.getId() == 0) {
			user.setPassword(encodePassword(user.getPassword()));
			user.setActive(false);
		}

		return userRepository.save(user);
	}

	@Override
	public void delete(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public User find(int id) {

		User user = userRepository.findById(id).get();
		user.setAuthorities(getAuthorities(user.getRoles()));

		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username);

		if (user == null)
			throw new UsernameNotFoundException(USERNAME_NOT_FOUND);

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

	@Override
	public void log(String method, String interchange, Object object) throws JsonProcessingException {

		String log = method.concat(", " + interchange).concat(": " + new ObjectMapper().writeValueAsString(object));
		logger.info(log);
	}
}
