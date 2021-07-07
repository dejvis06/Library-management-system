package com.example.lms.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.Book;
import com.example.lms.service.BookService;
import com.example.lms.util.HttpResponse;

@RestController
@RequestMapping("/book")
public class BookController {

	@Autowired
	private BookService bookService;

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<HttpResponse<Book>> save(@RequestBody Book book) {

		book = bookService.save(book);
		return createHttpResponse(book, OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<HttpResponse<Book>> find(@RequestParam("id") int id) {

		Book user = bookService.find(id);
		return createHttpResponse(user, OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public ResponseEntity<HttpResponse<Book>> delete(@RequestParam("id") int id) {

		return createHttpResponse(null, OK);
	}

	private ResponseEntity<HttpResponse<Book>> createHttpResponse(Book book, HttpStatus httpStatus) {

		HttpResponse<Book> httpResponse = new HttpResponse<Book>(httpStatus, httpStatus.value(),
				httpStatus.getReasonPhrase(), book);
		return new ResponseEntity<HttpResponse<Book>>(httpResponse, httpStatus);
	}

}
