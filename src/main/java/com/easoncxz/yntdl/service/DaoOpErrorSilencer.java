package com.easoncxz.yntdl.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class DaoOpErrorSilencer {

	private static Logger logger = LoggerFactory
			.getLogger(DaoOpErrorSilencer.class);

	// @Around(
	// value =
	// "execution(java.util.List<com.easoncxz.yntdl.domain.User> Dao.*(..)) ||"
	// + "execution(com.easoncxz.yntdl.domain.User Dao.*(..))")
	public Object silence(ProceedingJoinPoint joinpoint) {
		logger.info("DaoOpErrorSilencer advice is running");
		try {
			return joinpoint.proceed();
		} catch (Throwable e) {
			logger.error("Dao operation failed!!");
			e.printStackTrace();
			return null;
		}
	}

}
