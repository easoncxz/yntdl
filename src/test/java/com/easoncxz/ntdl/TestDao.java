package com.easoncxz.ntdl;

import java.util.Date;
import java.util.List;

import org.springframework.util.Assert;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

public class TestDao extends TestTemplate {

	public static void main(String[] args) {
		// Dao dao = TestTemplate.context.getBean("daoJdbcTemplate", Dao.class);
		Dao dao = TestTemplate.context.getBean("daoHibernate", Dao.class);
		Assert.notNull(dao);

		boolean flag = true;
		if (flag) {
			User u = new User();
			u.setName("John Doe -> " + new Date());
			dao.saveUser(u);
			Assert.notEmpty(dao.getAllUsers());
		}

		List<User> users = dao.getAllUsers();
		Assert.notNull(users);
		for (User u : users) {
			System.out.println(u.getName());
		}
	}
}
