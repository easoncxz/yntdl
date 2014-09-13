package com.easoncxz.yntdl.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;

@Aspect
public class ClientAuthenticationHelper {

	@Around(value = "execution(* *.getForObject(..))")
	public Users addHeader(ProceedingJoinPoint joinpoint) {
		// Users users = template.getForObject(URL_ALL_USERS, Users.class);

		Logger logger = LoggerFactory
				.getLogger(ClientAuthenticationHelper.class);
		logger.info("Yes, the advice is running");
		Users result = null;

		try {
			// result = (User) joinpoint.proceed();

			Object[] args = joinpoint.getArgs();
			String url = (String) args[0];
			Class clazz = (Class) args[1];

			RestTemplate template = (RestTemplate) joinpoint.getThis();

			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", "AuthFromClient");
			HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
			ResponseEntity<Users> response = template.exchange(url,
					HttpMethod.GET, httpEntity, Users.class);
			result = response.getBody();
			
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

		// return users;
	}
}
