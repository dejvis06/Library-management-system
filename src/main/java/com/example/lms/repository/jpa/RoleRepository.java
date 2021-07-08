package com.example.lms.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
