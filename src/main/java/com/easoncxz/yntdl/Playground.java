package com.easoncxz.yntdl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.Assert;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

public class Playground {

	public static void main(String[] args) {
		Logger l = LoggerFactory.getLogger(Playground.class);
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"client-context.xml");
		Dao dao = context.getBean("DaoJdbcTemplate", Dao.class);
		List<User> ul = dao.getAllUsers();
		Assert.isNull(ul);
		l.info("success");
	}

}
