package com.easoncxz.yntdl.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;

public class RestClientImpl implements RestClient {
	
	private static final String BASE_URL = "http://localhost:8080/yntdl/api/";
	private static final String URL_ALL_USERS = BASE_URL + "users/";
	private static final String URL_ONE_USER = BASE_URL + "users/{id}";
	
	private RestTemplate template;
	
	public void setRestTemplate(RestTemplate template) {
		this.template = template;
	}

	@Override
	public List<User> getAllUsers() {
		Users users = template.getForObject(URL_ALL_USERS, Users.class);
		return users.getUsers();
	}

	@Override
	public User getUserById(Long id) {
		return template.getForObject(URL_ONE_USER, User.class, id);
	}

	@Override
	public User save(User user) {
		return template.postForObject(URL_ALL_USERS, user, User.class);
	}

	@Override
	public User update(User user) {
		template.put(URL_ONE_USER, user, user.getId());
		return user;
	}

	@Override
	public void delete(User user) {
		template.delete(URL_ONE_USER, user.getId());
	}

}
