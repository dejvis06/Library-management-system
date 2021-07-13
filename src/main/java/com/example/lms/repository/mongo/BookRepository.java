package com.example.lms.repository.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.lms.entity.jpa.Book;

@Repository("bookRepositoryMongo")
public interface BookRepository extends MongoRepository<Book, Integer> {

}
