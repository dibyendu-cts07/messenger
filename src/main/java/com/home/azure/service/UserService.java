package com.home.azure.service;

import java.util.List;

import com.home.azure.model.User;

public interface UserService {
	public boolean saveUser(User user) throws Exception;
	public boolean deleteUser(String id) throws Exception;
	public User getUserById(String id) throws Exception;
	public List<User> getAllUsers() throws Exception;
}