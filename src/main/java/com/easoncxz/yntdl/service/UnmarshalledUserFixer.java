package com.easoncxz.yntdl.service;

import static com.easoncxz.yntdl.util.MyUtils.unmarshalledUserFixer;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.easoncxz.yntdl.domain.User;

@Aspect
@Component
public class UnmarshalledUserFixer {

	@Around(
			value = "execution(* com.easoncxz.yntdl.service.Service.*(String, com.easoncxz.yntdl.domain.User))")
	public Object unmarshallCleaning(ProceedingJoinPoint joinpoint) {
		String token = null;
		User u = null;
		for (Object o : joinpoint.getArgs()) {
			if (o != null && o instanceof User) {
				u = (User) o;
			} else if (o != null && o instanceof String) {
				token = (String) o;
			}
		}
		if (token != null && u != null) {
			unmarshalledUserFixer(u);
			try {
				return joinpoint.proceed(new Object[] { token, u });
			} catch (Throwable e) {
				throw new YntdlServiceAdviceException(
						"It's not the advice's fault - the method errored", e);
			}
		} else {
			String msg = "the UnmarshalledUserFixer advice saw null arguments";
			throw new YntdlServiceAdviceException(msg);
		}
	}

}
