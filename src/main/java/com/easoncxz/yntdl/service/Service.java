package com.easoncxz.yntdl.service;

import java.util.List;

import com.easoncxz.yntdl.domain.User;

public interface Service {

	List<User> getAllUsers(String token);

	User getUserById(String token, Long id);

	User save(String token, User user);

	User update(String token, User user);

	void delete(String token, User user);

}
