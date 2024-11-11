package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository repository;
	private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

	@Override
	public String registerUser(User user) {
		if (repository.existsByUsername(user.getUsername())) {
			return "Username already exists..";
		} else if (repository.existsByEmail(user.getEmail())) {
			return "Email already exists..";
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repository.save(user);
		return "User Registered Successfully...";
	}

	@Override
	public boolean loginUser(String username, String password) {
		Optional<User> loginUser = repository.findByUsername(username);
		// password.equals(loginUser.get().getPassword()) for normal password
		return loginUser.isPresent() && passwordEncoder.matches(password, loginUser.get().getPassword());

	}

}
