package com.example.lms.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Book;
import com.example.lms.service.BookService;
import com.example.lms.util.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/book")
public class BookController implements ControllerInterface<Book> {

	private static final String FIND = "FIND";

	private static final String DELETE = "DELETE";

	private static final String SAVE = "SAVE";

	private static final String REQUEST = "REQUEST";

	private static final String RESPONSE = "RESPONSE";

	@Autowired
	private BookService bookService;

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<HttpResponse<Book>> save(@RequestBody Book book) throws JsonProcessingException {

		bookService.log(SAVE, REQUEST, book);

		HttpResponse<Book> httpResponse = bookService.createHttpResponse(book, OK);
		bookService.log(SAVE, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<Book>>(httpResponse, OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<HttpResponse<Book>> find(@RequestParam("id") int id) throws JsonProcessingException {

		bookService.log(FIND, REQUEST, "id: " + id);

		Book user = bookService.find(id);

		HttpResponse<Book> httpResponse = bookService.createHttpResponse(user, OK);
		bookService.log(FIND, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<Book>>(httpResponse, OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public ResponseEntity<HttpResponse<Book>> delete(@RequestParam("id") int id) throws JsonProcessingException {

		bookService.log(DELETE, REQUEST, "id: " + id);

		bookService.delete(id);

		HttpResponse<Book> httpResponse = bookService.createHttpResponse(null, OK);
		bookService.log(DELETE, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<Book>>(httpResponse, OK);

	}

}
