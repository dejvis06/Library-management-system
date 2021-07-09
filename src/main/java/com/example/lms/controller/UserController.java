package com.example.lms.controller;

import static org.springframework.http.HttpStatus.OK;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
import com.example.lms.service.ServiceInterface;
import com.example.lms.service.UserInterface;
import com.example.lms.service.mysql.UserService;
import com.example.lms.util.HttpResponse;
import com.example.lms.util.SecurityConstant;
import com.example.lms.util.UserCustody;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/user")
//@Profile("mysql")
public class UserController implements ControllerInterface<User> {

	private static final String FIND = "FIND";

	private static final String DELETE = "DELETE";

	private static final String SAVE = "SAVE";

	private static final String REQUEST = "REQUEST";

	private static final String RESPONSE = "RESPONSE";

	private static final String LOGIN = "LOGIN";

	/*
	 * @Autowired private UserService userService;
	 */

	@Autowired
	private JWTTokenProvider jwtTokenProvider;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserInterface userService;

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ResponseEntity<HttpResponse<User>> login(@RequestBody User user) throws JsonProcessingException {

		userService.log(LOGIN, REQUEST, user);

		authenticate(user.getUsername(), user.getPassword());

		UserCustody userCustody = (UserCustody) userService.findByUsername(user.getUsername());

		HttpHeaders header = getHeaderWithJwt(userCustody);

		HttpResponse<User> response = new HttpResponse<User>(OK, OK.value(), OK.getReasonPhrase(),
				userCustody.getUser());
		userService.log(LOGIN, RESPONSE, response);

		return new ResponseEntity<>(new HttpResponse<User>(OK, OK.value(), OK.getReasonPhrase(), userCustody.getUser()),
				header, HttpStatus.OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/find")
	public ResponseEntity<HttpResponse<User>> find(@RequestParam("id") int id) throws JsonProcessingException {

		userService.log(FIND, REQUEST, "id: " + id);

		User user = userService.find(id);

		HttpResponse<User> httpResponse = userService.createHttpResponse(user, OK);
		userService.log(FIND, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<User>>(httpResponse, OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.GET, value = "/delete")
	public ResponseEntity<HttpResponse<User>> delete(@RequestParam("id") int id) throws JsonProcessingException {

		userService.log(DELETE, REQUEST, "id: " + id);

		userService.delete(id);

		HttpResponse<User> httpResponse = userService.createHttpResponse(null, OK);
		userService.log(DELETE, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<User>>(httpResponse, OK);
	}

	@Override
	@RequestMapping(method = RequestMethod.POST, value = "/save")
	public ResponseEntity<HttpResponse<User>> save(@RequestBody User user) throws JsonProcessingException {

		userService.log(SAVE, REQUEST, user);

		user = userService.save(user);

		HttpResponse<User> httpResponse = userService.createHttpResponse(user, OK);
		userService.log(SAVE, RESPONSE, httpResponse);

		return new ResponseEntity<HttpResponse<User>>(httpResponse, OK);
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
