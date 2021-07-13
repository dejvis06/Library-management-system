package com.example.lms.repository;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.lms.entity.User;

@NoRepositoryBean
public interface UserRepository extends PagingAndSortingRepository<User, Integer>, QueryByExampleExecutor<User> {

	public User findByUsername(String username);
}
