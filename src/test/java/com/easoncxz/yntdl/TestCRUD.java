package com.easoncxz.yntdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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

	private static RestClient crudder;

	private User defaultUser;

	@BeforeClass
	public static void metaSetup() {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"test-context.xml");
		crudder = context.getBean("restClient", RestClient.class);
	}

	@Before
	public void setUp() throws Exception {
		defaultUser = new User();
		defaultUser.setName("test user");
		TaskList list = new TaskList();
		list.setName("test list");
		Task task = new Task();
		task.setTitle("test task");

		list.addTask(task);
		defaultUser.addTaskList(list);

		defaultUser = crudder.save(defaultUser);

		assertNotNull(defaultUser.getId());
		assertNotNull(defaultUser.getTaskLists().get(0).getId());
		assertNotNull(defaultUser.getTaskLists().get(0).getTasks().get(0)
				.getId());
	}

	@After
	public void tearDown() throws Exception {
		if (defaultUser != null) {
			crudder.delete(defaultUser);
		}
	}

	@Test
	public void testCreateUser() {
		// done in setUp.
	}

	@Test
	public void testRetrieveAllUsers() {
		List<User> users = crudder.getAllUsers();
		assertNotEquals(0, users.size());
	}

	@Test
	public void testRetrieveUserById() {
		String uname = defaultUser.getName();
		Long id = defaultUser.getId();

		User u = crudder.getUserById(id);
		assertEquals(uname, u.getName());
	}

	@Test
	public void testUpdateUser() {
		Long id = defaultUser.getId();
		defaultUser.setName("new name");
		defaultUser = crudder.update(defaultUser);

		User u = crudder.getUserById(id);
		assertEquals("new name", u.getName());
	}

	@Test
	public void testDeleteUser() {
		Long id = defaultUser.getId();
		crudder.delete(defaultUser);

		User u = crudder.getUserById(id);
		assertNull(u);
	}

}
