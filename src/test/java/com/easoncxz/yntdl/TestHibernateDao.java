package com.easoncxz.yntdl;

import static org.junit.Assert.*;

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
	public void testCreateUser() {
		User u = new User();
		u.setName("Name Before");
		assertNull(u.getId());
		dao.save(u);
		assertNotNull(u);
		assertNotNull(u.getId());
	}

	@Test
	public void testUpdateUser() {
		User u = new User();
		u.setName("Name Before");
		dao.save(u);

		u.setName("Name Modded");
		Long id = u.getId();
		dao.update(u);
		assertEquals(id, u.getId());
		assertEquals("Name Modded", dao.getUserById(id).getName());
	}
	
	@Test
	public void testDeleteUser() {
		User u = new User();
		u.setName("Name Before");
		dao.save(u);
		Long id = u.getId();
		assertNotNull(id);
		dao.delete(u);
		assertNull(dao.getUserById(id));
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
