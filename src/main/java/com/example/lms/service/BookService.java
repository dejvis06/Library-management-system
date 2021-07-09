package com.example.lms.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.example.lms.entity.Book;
import com.example.lms.repository.jpa.BookRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@Profile("mysql")
public class BookService implements ServiceInterface<Book> {

	private static final Logger logger = LogManager.getLogger(BookService.class.getSimpleName());

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

	@Override
	public void log(String method, String interchange, Object object) throws JsonProcessingException {
		String log = method.concat(", " + interchange).concat(": " + new ObjectMapper().writeValueAsString(object));
		logger.info(log);
	}
}
