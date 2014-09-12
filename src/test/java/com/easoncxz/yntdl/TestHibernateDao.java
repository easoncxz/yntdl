package com.easoncxz.yntdl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Test
	public void testAttachTaskListToUser() {
		User u = new User();
		u.setName("John");
		TaskList l = new TaskList();
		l.setName("Some list");
		u.addTaskList(l);
		assertTrue(u.getTaskLists().size() == 1);
		dao.save(u);
		assertTrue(u.getTaskLists().size() == 1);
		Long id = u.getId();
		assertNotNull(id);

		List<TaskList> lists = dao.getUserById(id).getTaskLists();
		logger.info("The list of TaskLists is actually a: "
				+ lists.getClass().getName());
		assertEquals(1, lists.size());
		assertEquals("Some list", lists.get(0).getName());
	}

	@Test
	public void testDetachTaskListFromUser() {
		User u = new User();
		u.setName("John");
		TaskList l = new TaskList();
		l.setName("ListOne");
		u.addTaskList(l);
		dao.save(u);
		Long id = u.getId();
		assertEquals("ListOne", dao.getUserById(id).getTaskLists().get(0)
				.getName());

		u.deleteTaskList(l);
		dao.update(u);
		assertEquals(0, dao.getUserById(id).getTaskLists().size());
	}

	@SuppressWarnings("deprecation")
	@Test
	public void testAttachTaskToTaskListThenUser() {
		Long id;
		{
			User u = new User();
			u.setName("John");

			TaskList l = new TaskList();
			l.setName("Default task list");

			Task t = new Task();
			t.setTitle("Task title");
			t.setContents("Something is due soon");
			t.setLastModifiedDate(new Date());
			t.setDueDate(new Date(114, 8, 15));

			l.addTask(t);
			u.addTaskList(l);
			dao.save(u);
			id = u.getId();
			assertNotNull(id);
		}

		{
			User user = dao.getUserById(id);
			List<TaskList> lists = user.getTaskLists();
			assertNotEquals(0, lists.size());

			TaskList list = lists.get(0);
			List<Task> tasks = list.getTasks();
			assertNotEquals(0, tasks.size());

			Task task = tasks.get(0);
			assertNotNull(task);

			assertEquals("Task title", task.getTitle());
			assertEquals("Something is due soon", task.getContents());
			assertEquals("Default task list", list.getName());
			assertEquals("John", user.getName());
		}
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void testDetachTaskFromTaskListGivenUser() {
		Long id;
		{
			User u = new User();
			u.setName("John");

			TaskList l = new TaskList();
			l.setName("Default task list");

			Task t = new Task();
			t.setTitle("Task title");
			t.setContents("Something is due soon");
			t.setLastModifiedDate(new Date());
			t.setDueDate(new Date(114, 8, 15));

			l.addTask(t);
			u.addTaskList(l);
			dao.save(u);
			id = u.getId();
			assertNotNull(id);
		}
		{
			User user = dao.getUserById(id);
			TaskList list = user.getTaskLists().get(0);
			Task task = list.getTasks().get(0);

			list.deleteTask(task);
			assertSame(user.getTaskLists().get(0), list);
			dao.update(user);
			assertEquals(id, user.getId());
		}
		{
			User user = dao.getUserById(id);
			TaskList list = user.getTaskLists().get(0);
			assertEquals(0, list.getTasks().size());
		}
	}
	
	@Test
	public void testJavaSetToAndFromListShallowConvert() {
		Object o1 = new Object();
		Set<Object> set = new HashSet<Object>();
		set.add(o1);
		List<Object> list = new ArrayList<Object>(set);
		Object o2 = list.get(0);
		assertSame(o1, o2);
		assertTrue(o1 == o2);
		Set<Object> set2 = new HashSet<Object>(list);
		Object o3 = (new ArrayList<Object>(set2)).get(0);
		assertSame(o1, o3);
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
			assertNull(dao.getUserById(u.getId()));
			dao.save(u);
		}
		assertEquals(beforeTests.size(), dao.getAllUsers().size());
	}

}
