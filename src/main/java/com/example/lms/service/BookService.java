package com.example.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Book;
import com.example.lms.repository.BookRepository;

@Service
public class BookService implements ServiceInterface<Book> {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book find(int id) {
		return bookRepository.findById(id).get();
	}
}
