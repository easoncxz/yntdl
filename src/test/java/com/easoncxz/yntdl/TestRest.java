package com.easoncxz.yntdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class TestRest {

	RestTemplate template;

	ApplicationContext context;

	final String BASE_URL = "http://localhost:8080/yntdl/";
	final String URL_CREATE_USER = BASE_URL + "api/users/";
	final String URL_UPDATE_USER = BASE_URL + "api/users/{id}";

	Logger logger = LoggerFactory.getLogger(TestRest.class);

	@Before
	public void setUp() throws Exception {
		context = new ClassPathXmlApplicationContext("test-context.xml");
		template = context.getBean(RestTemplate.class);
		assertNotNull(context);
		assertNotNull(template);
	}

	private static class ULT {
		User u;
		TaskList l;
		Task t;
	}

	ULT basicLocalSetup() {
		User user = new User();
		user.setName("RestTestUser");

		TaskList list = new TaskList();
		list.setName("RestTestList");

		Task task = new Task();
		task.setTitle("RestTestTitle");

		list.addTask(task);
		user.addTaskList(list);

		ULT result = new ULT();
		result.u = user;
		result.l = list;
		result.t = task;
		return result;
	}

	@Test
	public void testCreation() {
		ULT thing = basicLocalSetup();
		User user = thing.u;
		TaskList list = thing.l;
		Task task = thing.t;
		
		assertNull(user.getId());
		assertNull(list.getId());
		assertNull(task.getId());
		
		user = template.postForObject(URL_CREATE_USER, user, User.class);
		
		assertNotNull(user.getId());
		list = user.getTaskLists().get(0);
		assertNotNull(list.getId());
		task = list.getTasks().get(0);
		assertNotNull(task.getId());
	}

	@Test
	public void testCallingUpdateToCreate() {
		ULT o = basicLocalSetup();
		User user = template.postForObject(URL_CREATE_USER, o.u, User.class);
		
		// here's the interesting bit
		TaskList newList = new TaskList();
		newList.setName("User's *second* list");
		user.addTaskList(newList);

		// update should return updated object
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", user.getId());
		user = template.postForObject(URL_UPDATE_USER, user, User.class, params);
		
		assertEquals(2, user.getTaskLists().size());
	}

}
