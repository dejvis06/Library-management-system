package com.example.lms.repository.mongo;

import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.User;

@Repository("userMongoRepository")
@Profile("mongo")
public interface UserRepository extends MongoRepository<User, Integer>, com.example.lms.repository.UserRepository {

}
