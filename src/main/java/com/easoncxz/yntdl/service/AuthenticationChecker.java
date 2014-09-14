package com.easoncxz.yntdl.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthenticationChecker {

	private Logger logger = LoggerFactory
			.getLogger(AuthenticationChecker.class);

//	@Around(
//			value = "execution(* com.easoncxz.yntdl.service.Service.*(String, ..))")
	public void checkToken(ProceedingJoinPoint joinpoint) {
		String msg = "AuthenticationChecker advice is running!";
		logger.info(msg);
		// try {
		// joinpoint.proceed();
		// } catch (Throwable e) {
		// msg = e.getMessage();
		// e.printStackTrace();
		// }
		throw new RuntimeException(msg);
	}

}
