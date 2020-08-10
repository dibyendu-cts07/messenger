package com.home.azure.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.home.azure.model.User;
import com.home.azure.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() throws Exception {
		return userRepository.findAll().collectList().block();
	}

	public User getUserById(String id) throws Exception {
		Mono<User> user = userRepository.findById(id);
		if (user.block() == null) {
			throw new Exception("User not found");
		}
		return user.block();
	}

	public boolean saveUser(User user) throws Exception {
		try {
			final Mono<User> savedUser = userRepository.save(user);
			savedUser.block();
		} catch (Exception ex) {
			throw ex;
		}
		return true;
	}

	public boolean deleteUser(String id) throws Exception {
		Mono<User> user = userRepository.findById(id);
		if (user.block() == null) {
			throw new Exception("User not found");
		}
		userRepository.delete(user.block());
		return true;
	}

}
