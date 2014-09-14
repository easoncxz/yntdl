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

	private static Logger logger = LoggerFactory
			.getLogger(AuthenticationChecker.class);

	private static boolean isValid(String token) {
		logger.info("validating token: " + token);
		return token == null ? false : "johndoe:1234".equals(token);
	}

	@Around(
			value = "execution(* com.easoncxz.yntdl.service.Service.*(String, ..))")
	public Object checkToken(ProceedingJoinPoint joinpoint) {
		logger.info("AuthenticationChecker advice is running!");
		String token = null;
		for (Object o : joinpoint.getArgs()) {
			if (o != null && o instanceof String) {
				token = (String) o;
				break;
			}
		}
		if (isValid(token)) {
			try {
				return joinpoint.proceed();
			} catch (Throwable e) {
				String msg = "The target method error. None of the advice's business. Nested exception follows:";
				throw new YntdlServiceAdviceException(msg, e);
			}
		} else {
			// when credentials are invalid,
			// return null as target object method result
			return null;
		}
	}
}
