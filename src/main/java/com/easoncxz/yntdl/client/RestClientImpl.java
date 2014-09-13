package com.easoncxz.yntdl.client;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.support.HttpRequestWrapper;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;

public class RestClientImpl implements RestClient {

	private static final String BASE_URL = "http://localhost:8080/yntdl/api/";
	private static final String URL_ALL_USERS = BASE_URL + "users/";
	private static final String URL_ONE_USER = BASE_URL + "users/{id}";

	private RestTemplate template;

	/**
	 * Discovered from <a href=
	 * "http://svenfila.wordpress.com/2012/01/05/resttemplate-with-custom-http-headers/"
	 * >http://svenfila.wordpress.com/2012/01/05/resttemplate-with-custom-http-
	 * headers/</a>
	 */
	private static class MyHttpRequestInterceptor implements
			ClientHttpRequestInterceptor {

		private final String headerValue;

		MyHttpRequestInterceptor(String headerValue) {
			this.headerValue = headerValue;
		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body,
				ClientHttpRequestExecution execution) throws IOException {

			// HttpRequestWrapper requestWrapper = new
			// HttpRequestWrapper(request);
			// request.getHeaders().setAccept(
			// Arrays.asList(MediaType.valueOf(headerValue)));

			request.getHeaders().add("Authorization", this.headerValue);

			return execution.execute(request, body);
		}
	}

	public void setRestTemplate(RestTemplate template) {
		// this.template = new RestTemplateSub(template, this);
		this.template = template;
		ClientHttpRequestInterceptor intc = new MyHttpRequestInterceptor(
				currentUsername + ":" + currentPassword);
		this.template.setInterceptors(Arrays.asList(intc));
	}

	private String currentUsername;
	private String currentPassword;

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

	@Override
	public void login(String username, String password) {
		this.currentUsername = username;
		this.currentPassword = password;
	}

	@Override
	public void logout() {
		this.currentUsername = null;
		this.currentPassword = null;
	}

	public String getCurrentUsername() {
		return currentUsername;
	}

	public String getCurrentPassword() {
		return currentPassword;
	}

}
