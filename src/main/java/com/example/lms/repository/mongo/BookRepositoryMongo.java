package com.example.lms.repository.mongo;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.lms.entity.Book;

@Qualifier(value = "mongoBookRepository")
public interface BookRepositoryMongo extends MongoRepository<Book, Integer> {

}
