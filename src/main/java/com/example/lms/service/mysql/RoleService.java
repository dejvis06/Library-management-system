package com.example.lms.service.mysql;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lms.entity.jpa.Role;
import com.example.lms.repository.jpa.RoleRepository;
import com.example.lms.service.ServiceInterface;
import com.fasterxml.jackson.core.JsonProcessingException;

@Service
@Profile("mysql")
public class RoleService implements ServiceInterface<Role> {

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}

	@Override
	public void delete(int id) {
		roleRepository.deleteById(id);
	}

	@Override
	public Role find(int id) {
		return roleRepository.findById(id).get();
	}

	@Override
	public void log(String method, String interchange, Object object) throws JsonProcessingException {
		// TODO Auto-generated method stub
	}
}
