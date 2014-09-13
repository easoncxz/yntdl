package com.easoncxz.yntdl.client;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.User;

public class RestClientImpl implements RestClient {
	
	private RestTemplate template;
	
	public void setRestTemplate(RestTemplate template) {
		this.template = template;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub

	}

}
