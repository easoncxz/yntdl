package com.easoncxz.yntdl.client;

import java.util.List;

import com.easoncxz.yntdl.domain.User;

/**
 * Just because the client needs to be able to do the same CRUD operations as
 * the server.
 * 
 * @author eason
 *
 */
public interface RestClient {

	List<User> getAllUsers();
	
	User getUserById(Long id);
	
	void save(User user);
	
	void update(User user);
	
	void delete(User user);

}
