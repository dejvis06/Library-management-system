package com.example.lms.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.jpa.Role;

@Repository
@Profile("mysql")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
