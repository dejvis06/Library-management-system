package com.example.lms.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Role;
import com.example.lms.entity.User;
import com.example.lms.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	@Transactional
	public Role save(Role role) throws Exception {
		return roleRepository.save(role);
	}

	@Transactional
	public void delete(Role role) throws Exception {
		roleRepository.delete(role);
	}

	public Role find(Role role) throws NoSuchElementException {
		return roleRepository.findById(role.getId()).get();
	}
}
