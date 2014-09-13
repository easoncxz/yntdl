package com.easoncxz.yntdl;

import static com.easoncxz.yntdl.util.MyUtils.dumpUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easoncxz.yntdl.client.RestClient;
import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class TestCRUD {

	private static RestClient crudder;
	private static Logger logger = LoggerFactory.getLogger(TestCRUD.class);
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
		Long uid = defaultUser.getId();
		String lname = defaultUser.getTaskLists().get(0).getName();
		Long lid = defaultUser.getTaskLists().get(0).getId();
		String ttitle = defaultUser.getTaskLists().get(0).getTasks().get(0).getTitle();
		Long tid = defaultUser.getTaskLists().get(0).getTasks().get(0).getId();

		User u = crudder.getUserById(uid);
		assertEquals(uname, u.getName());
		assertEquals(uid, u.getId());

		TaskList l = u.getTaskLists().get(0);
		assertEquals(lname, l.getName());
		assertEquals(lid, l.getId());

		Task t = l.getTasks().get(0);
		assertEquals(ttitle, t.getTitle());
		assertEquals(tid, t.getId());
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
	
	@Test
	public void testCreateListByUpdatingUser() {
		Long id = defaultUser.getId();
		assertNotNull(id);
		TaskList newList = new TaskList();
		newList.setName("a *second* list");
		defaultUser.addTaskList(newList);
		crudder.update(defaultUser);
		
		User u = crudder.getUserById(id);
		assertEquals(2, u.getTaskLists().size());
	}
	
	@Test
	public void testUpdateListByUpdatingUser() {
		Long id = defaultUser.getId();
		assertNotNull(id);
		Task t = new Task();
		t.setTitle("a new task!");
		defaultUser.getTaskLists().get(0).addTask(t);
		crudder.update(defaultUser);
		
		User u = crudder.getUserById(id);
		List<Task> tasks = u.getTaskLists().get(0).getTasks();
		assertEquals(2, tasks.size());
		boolean success = false;
		for (Task task : tasks) {
			if (task.getTitle().equals("a new task!")) {
				success = true;
				break;
			}
		}
		assertTrue(success);
	}
	
	@Test
	public void testDeleteListByUpdatingUser() {
		Long id = defaultUser.getId();
		assertNotNull(id);
		TaskList l = defaultUser.getTaskLists().get(0);
		defaultUser.deleteTaskList(l);
		User user = crudder.update(defaultUser);
		assertEquals(0, user.getTaskLists().size());
		logger.info("TestCRUD testDeleteListByUpdatingUser - now dump user after op:");
		dumpUser(logger, user);
	}

}
