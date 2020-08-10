package com.home.azure.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.home.azure.model.User;
import com.home.azure.service.UserService;

@Controller
@RequestMapping(path = "/user")
@CrossOrigin(origins = "*")
public class UserController implements Constants {

	@Autowired
	private UserService userService;

	@GetMapping
	public @ResponseBody ResponseEntity<?> getAllUsers(final HttpServletRequest req, final HttpServletResponse res)
			throws IOException {
		try {
			List<User> users = userService.getAllUsers();

			return new ResponseEntity<List<User>>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Exception:" + e, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping(path = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> getUserById(@PathVariable("id") String id) {
		try {
			User user = userService.getUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public @ResponseBody ResponseEntity<?> saveUser(@RequestBody User user, final ServletRequest req,
			final ServletResponse res) {
		try {
			userService.saveUser(user);

		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.CONFLICT);
		}
		return new ResponseEntity<User>(user, HttpStatus.CREATED);
	}

	@DeleteMapping(value = "/{id:[0-9]+}")
	public @ResponseBody ResponseEntity<?> deleteUser(@PathVariable("id") String id) {
		try {
			userService.deleteUser(id);
		} catch (Exception e) {
			return new ResponseEntity<Integer>(-1, HttpStatus.OK);
		}
		return new ResponseEntity<Integer>(0, HttpStatus.OK);
	}

}