package com.easoncxz.yntdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

public class TestHibernateDao {

	private Dao dao;

	private ApplicationContext context;

	private List<User> beforeTests;

	private Logger logger = LoggerFactory.getLogger(TestHibernateDao.class);

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("test-context.xml");
		assertNotNull(context);
	
		dao = context.getBean("daoHibernate", Dao.class);
		assertNotNull(dao);
	
		beforeTests = dao.getAllUsers();
		logger.info(beforeTests.toString());
	
		for (User u : beforeTests) {
			assertNotNull(u);
			dao.delete(u);
		}
		assertEquals(0, dao.getAllUsers().size());
	}

	@Test
	public void test() {
		assertEquals(0, dao.getAllUsers().size());
		User u = new User();
		u.setName("Name from JUnit");
		dao.save(u);
		assertNotNull(u);
		assertNotNull(u.getId());
		assertEquals(1, dao.getAllUsers().size());
	}

	@After
	public void tearDown() throws Exception {
		for (User u : dao.getAllUsers()) {
			assertNotNull(u);
			dao.delete(u);
		}
		for (User u : beforeTests) {
			assertNotNull(u);
			assertNotNull(u.getId());
			dao.save(u);
		}
		assertEquals(beforeTests.size(), dao.getAllUsers().size());
	}

}
