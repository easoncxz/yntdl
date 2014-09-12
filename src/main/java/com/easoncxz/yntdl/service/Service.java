package com.easoncxz.yntdl.service;

import java.util.List;

import com.easoncxz.yntdl.domain.User;

public interface Service {
	
	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);

}
