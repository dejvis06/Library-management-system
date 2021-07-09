package com.example.lms.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.User;

@Repository
@Profile("mysql")
public interface UserRepository extends JpaRepository<User, Integer> {

	public User findByUsername(String username);
}
