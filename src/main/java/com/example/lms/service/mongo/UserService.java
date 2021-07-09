package com.example.lms.service.mongo;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.lms.entity.User;
import com.example.lms.service.ServiceInterface;
import com.example.lms.service.UserInterface;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service("userMongoService")
public class UserService implements ServiceInterface<User>, UserInterface {

	@Override
	public User save(User object) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(int id) {
		// TODO Auto-generated method stub
		System.err.println("uau, id: " + id);
		return null;
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void log(String method, String interchange, Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub

	}

	@Override
	public UserDetails findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
