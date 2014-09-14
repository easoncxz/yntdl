package com.easoncxz.yntdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.service.Service;

public class TestService {

	private ApplicationContext context;

	private List<User> beforeTests;

	private Logger logger = LoggerFactory.getLogger(TestService.class);

	private Service service;
	
	private final String token = "TestServiceUser:pw";
	
	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("test-context.xml");
		assertNotNull(context);

		service = context.getBean("serverService", Service.class);
		assertNotNull(service);

		beforeTests = service.getAllUsers(token);
		logger.info(beforeTests.toString());

		for (User u : beforeTests) {
			assertNotNull(u);
			assertNotNull(u.getId());
			service.delete(token, u);
		}
		assertEquals(0, service.getAllUsers(token).size());
	}

	@Test
	public void testRecursivelyGeneratedId() {
		User u = new User();
		TaskList l = new TaskList();
		Task t = new Task();
		l.addTask(t);
		u.addTaskList(l);
		assertNull(u.getId());
		assertNull(l.getId());
		assertNull(t.getId());
		service.save(token, u);
		assertNotNull(u.getId());
		assertNotNull(l.getId());
		assertNotNull(t.getId());
	}

	@After
	public void tearDown() throws Exception {
		for (User u : service.getAllUsers(token)) {
			assertNotNull(u);
			service.delete(token, u);
		}
		for (User u : beforeTests) {
			assertNotNull(u);
			assertNotNull(u.getId());
			assertNull(service.getUserById(token, u.getId()));
			service.save(token, u);
		}
		assertEquals(beforeTests.size(), service.getAllUsers(token).size());
	}
}
