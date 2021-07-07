package com.example.lms;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.example.lms.entity.User;
import com.example.lms.service.BookService;
import com.example.lms.service.RoleService;
import com.example.lms.service.UserService;

@SpringBootTest
class LmsApplicationTests {

	@Autowired
	private UserService userService;
	
	@Autowired 
	private RoleService roleService;
	
	@Autowired
	private BookService bookService;

	@Test
	@Rollback(false)
	void dataEntry() {

		try {

			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
