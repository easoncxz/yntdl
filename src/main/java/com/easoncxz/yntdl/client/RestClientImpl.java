package com.easoncxz.yntdl.client;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;

public class RestClientImpl implements RestClient {

	private static final String BASE_URL = "http://localhost:8080/yntdl/api/";
	private static final String URL_ALL_USERS = BASE_URL + "users/";
	private static final String URL_ONE_USER = BASE_URL + "users/{id}";

	private RestTemplate template;

	public void setRestTemplate(RestTemplate template) {
		this.template = new RestTemplateSub(template, this);
	}
	
	private String currentUsername;
	private String currentPassword;

	@Override
	public List<User> getAllUsers() {
		Users users = null;
		// Users users = template.getForObject(URL_ALL_USERS, Users.class);
		HttpHeaders headers = new HttpHeaders();
		headers.set("Authorization", "AuthFromClient");
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		ResponseEntity<Users> response = template.exchange(URL_ALL_USERS, HttpMethod.GET, httpEntity, Users.class);
		users = response.getBody();
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

	@Override
	public void login(String username, String password) {
		this.currentPassword = username;
		this.currentPassword = password;
	}

	@Override
	public void logout() {
		this.currentPassword = null;
		this.currentPassword = null;

	}

}
