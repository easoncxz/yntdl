package com.easoncxz.yntdl.client;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.Users;

@Aspect
public class ClientAuthenticationHelper {

	private Logger logger = LoggerFactory
			.getLogger(ClientAuthenticationHelper.class);
	
	private RestClientImpl client;
	
	@Autowired
	public void setClient(RestClientImpl client) {
		this.client = client;
	}

//	@Around(value = "execution(* *.getForObject(java.lang.String, java.lang.Class, ..))")
	public Users addHeader(ProceedingJoinPoint joinpoint) {
		logger.info("Yes, the advice is running");
		logger.info("The 'client' field is null? -> " + (this.client == null));
		logger.info("The client's username/password: " + client.getCurrentUsername() + ":" + client.getCurrentPassword());
		logger.info("The 'this' object has class name: " + joinpoint.getThis().getClass().getName());
		logger.info("The target object has class name: " + joinpoint.getTarget().getClass().getName());
		Users result = null;
		try {
			Object[] args = joinpoint.getArgs();
			logger.info("The joinpoint has: " + args.length + " args");
			Object arg0 = args[0];
			Object arg2 = args[2];
			logger.info("arg0 instance of String? -> " + (arg0 instanceof String));
			logger.info("arg2 instance of Object[]? -> " + (arg2 instanceof Object[]));
			logger.info("The joinpoint is on method: " + joinpoint.getSignature());

//			Map<String, Object> map;
//			result = (Users) joinpoint.proceed(new Object[]{ args[0], args[1], map, args[2]});

//			String url = "http://localhost:8080/yntdl/api/users/";
//			RestTemplateSub template = (RestTemplateSub) joinpoint.getThis();

			String url = (String) args[0];
			RestTemplate template = (RestTemplate) joinpoint.getTarget();

//			HttpHeaders headers = new HttpHeaders();
//			headers.set("Authorization", "AuthFromAdvice");
//			HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
//			ResponseEntity<Users> response = template.exchange(url,
//					HttpMethod.GET, httpEntity, Users.class);
//			result = response.getBody();
			
			HttpRequest r = null;
//			r.getHeaders().a

//			result = (Users) template.getforobject
			
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return result;
	}
	
//	@Around(value = "execution(* com.easoncxz.yntdl.client.RestClient.login(java.lang.String, java.lang.String))")
	public void clientLogin(ProceedingJoinPoint joinpoint) {
		logger.info("sample advice is run");
		try {
			joinpoint.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
