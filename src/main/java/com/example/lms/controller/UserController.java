package com.example.lms.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lms.entity.User;
import com.example.lms.security.JWTTokenProvider;
import com.example.lms.service.UserService;
import com.example.lms.util.HttpResponse;
import com.example.lms.util.SecurityConstant;
import com.example.lms.util.UserCustody;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<HttpResponse<User>> login(@RequestBody User user) {

		authenticate(user.getUsername(), user.getPassword());

		UserCustody userCustody = (UserCustody) userService.loadUserByUsername(user.getUsername());

		HttpHeaders header = getHeaderWithJwt(userCustody);

		return new ResponseEntity<>(new HttpResponse<User>(OK, OK.value(), OK.getReasonPhrase(), userCustody.getUser()),
				header, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<HttpResponse<User>> find(@RequestParam("id") int id) {

		User user = userService.find(id);
		return createHttpResponse(user, OK);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<HttpResponse<User>> save(@RequestBody User user) {

		user = userService.save(user);
		return createHttpResponse(user, OK);
	}

	private ResponseEntity<HttpResponse<User>> createHttpResponse(User user, HttpStatus httpStatus) {

		HttpResponse<User> httpResponse = new HttpResponse<User>(httpStatus, httpStatus.value(),
				httpStatus.getReasonPhrase(), user);
		return new ResponseEntity<HttpResponse<User>>(httpResponse, httpStatus);
	}

	private HttpHeaders getHeaderWithJwt(UserCustody userCustody) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add(SecurityConstant.JWT_TOKEN_HEADER, jwtTokenProvider.generateJwtToken(userCustody));

		return httpHeaders;
	}

	private void authenticate(String username, String password) {
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
	}
}
