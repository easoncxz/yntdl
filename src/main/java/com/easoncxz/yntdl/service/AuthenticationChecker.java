package com.easoncxz.yntdl.service;

import static com.easoncxz.yntdl.util.MyUtils.unmarshalledUserFixer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.easoncxz.yntdl.domain.User;

@Aspect
@Component
public class AuthenticationChecker {

	private static Logger logger = LoggerFactory
			.getLogger(AuthenticationChecker.class);

	private static boolean isValid(String token) {
		logger.info("validating token: " + token);
		System.out.println("hello? - AuthenticationChecker.");
		return token == null ? false : "johndoe:1234".equals(token);
	}

	@Around(
			value = "execution(* com.easoncxz.yntdl.service.Service.*(String, com.easoncxz.yntdl.domain.User))")
	public Object unmarshallCleaning(ProceedingJoinPoint joinpoint) {
		try {
			String token = null;
			User u = null;
			for (Object o : joinpoint.getArgs()) {
				if (o instanceof User) {
					u = (User) o;
				} else if (o instanceof String) {
					token = (String) o;
				}
			}
			if (token != null && u != null) {
				unmarshalledUserFixer(u);
				return joinpoint.proceed(new Object[] { token, u });
			} else {
				throw new IllegalArgumentException("from advice");
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
		throw new IllegalArgumentException("from advice");
	}

	@Around(
			value = "execution(* com.easoncxz.yntdl.service.Service.*(String, ..))")
	public Object checkToken(ProceedingJoinPoint joinpoint) {
		logger.info("AuthenticationChecker advice is running!");
		String token = null;
		for (Object o : joinpoint.getArgs()) {
			if (o instanceof String) {
				token = (String) o;
				break;
			}
		}
		if (isValid(token)) {
			try {
				return joinpoint.proceed();
			} catch (Throwable e) {
				e.printStackTrace();
			}
			throw new RuntimeException("yntdl advice error");
		} else {
			throw new SecurityException("yntdl login failure");
		}
	}
}
