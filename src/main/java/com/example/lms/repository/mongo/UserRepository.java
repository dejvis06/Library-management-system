package com.example.lms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.User;

@Repository("userRepositoryMongo")
public interface UserRepository extends MongoRepository<User, Integer> {

}
