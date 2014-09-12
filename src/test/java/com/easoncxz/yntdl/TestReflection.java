package com.easoncxz.yntdl;

import static com.easoncxz.yntdl.util.MyUtils.containingListGetter;
import static com.easoncxz.yntdl.util.MyUtils.containingListSetter;
import static com.easoncxz.yntdl.util.MyUtils.ownerGetter;
import static com.easoncxz.yntdl.util.MyUtils.ownerSetter;
import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

public class TestReflection {

	private Dao dao;

	@Before
	public void setUp() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"test-context.xml");
		dao = context.getBean(Dao.class);
		assertNotNull(dao);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		User u = new User();
		TaskList l = new TaskList();
		Task t = new Task();
		t.setTitle("TestingTaskTitle");
		l.addTask(t);
		l.setName("TestingListName");
		u.addTaskList(l);
		u.setName("TestingUserName");

		assertEquals(u.getName(), ownerGetter(l).getName());
		assertSame(u, ownerGetter(l));
		assertEquals(l.getName(), containingListGetter(t).getName());
		assertSame(l, containingListGetter(t));

		ownerSetter(l, null);
		assertNull(ownerGetter(l));
		containingListSetter(t, null);
		assertNull(containingListGetter(t));
	}

}
