package com.example.service;

import com.example.model.User;

public interface UserService {

	boolean loginUser(String username, String password);

	String registerUser(User user);
}