package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Book;
import com.example.lms.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	public Book save(Book book) {
		return bookRepository.save(book);
	}

	public void delete(Book book) {
		bookRepository.delete(book);
	}

	public Book find(int id) {
		return bookRepository.findById(id).get();
	}
}
