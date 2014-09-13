package com.easoncxz.yntdl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easoncxz.yntdl.client.RestClient;
import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class TestCRUD {
	
	private static RestClient client;

	@BeforeClass
	public static void metaSetup() {
		ApplicationContext context = new ClassPathXmlApplicationContext("test-context.xml");
		client = context.getBean("restClient", RestClient.class);
	}

	@Before
	public void setUp() throws Exception {
		User user = new User();
		user.setName("test user");
		TaskList list = new TaskList();
		list.setName("test list");
		Task task = new Task();
		task.setTitle("test task");
		
		list.addTask(task);
		user.addTaskList(list);
		
		client.save(user);
		
		assertNotNull(user.getId());
		assertNotNull(user.getTaskLists().get(0).getId());
		assertNotNull(user.getTaskLists().get(0).getTasks().get(0).getId());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
