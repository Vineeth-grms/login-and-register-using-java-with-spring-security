package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;

	@PostMapping("/signup")
	public ResponseEntity<String> registerUser(@RequestBody User user) {
		String response = service.registerUser(user);
		if (response.equals("User Registered Successfully...")) {
			return ResponseEntity.status(HttpStatus.CREATED).body(response);
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
		}
	}

	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestBody User user) {
		boolean isAuthorized = service.loginUser(user.getUsername(), user.getPassword());
		if (isAuthorized) {
			return ResponseEntity.ok("User logged In successfully...");
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Username or password");
		}

	}

}
