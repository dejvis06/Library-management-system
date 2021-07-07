package com.example.lms.service;

import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Book;
import com.example.lms.entity.Role;
import com.example.lms.repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	@Transactional
	public Book save(Book book) throws Exception {
		return bookRepository.save(book);
	}

	@Transactional
	public void delete(Book book) throws Exception {
		bookRepository.delete(book);
	}

	public Book find(Book book) throws NoSuchElementException {
		return bookRepository.findById(book.getId()).get();
	}
}
