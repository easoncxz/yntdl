package com.easoncxz.yntdl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

public class TestDao extends TestTemplate {

	private static Logger logger = LoggerFactory.getLogger(TestDao.class);

	private static Dao dao = TestTemplate.context.getBean("daoHibernate",
			Dao.class);

	public static void main(String[] args) {
		Assert.notNull(dao);

		{
			// User u = dao.getUserById(22);
			User u = new User();
			u.setId(22L);
			Assert.notNull(u);
			// Assert.isTrue(u.getName().equals("test user"));
			List<TaskList> lists = dao.getOwnedTaskLists(u);
			Assert.isTrue(lists.size() == 2);
			Assert.isTrue(lists.get(0).getName().equals("default list"));
			Assert.isTrue(lists.get(1).getName().equals("other list"));
			logger.warn("success");
		}

		boolean returnNow = true;
		if (returnNow) {
			return;
		}

		{
			System.out.println();
			logger.warn("now creating another John Doe");
			User u = new User();
			Assert.isNull(u.getId());
			u.setName("John Doe -> " + new Date());
			dao.saveUser(u);
			Assert.notNull(u.getId());
		}

		{
			System.out.println();
			logger.warn("about to print all users");
			List<User> users = dao.getAllUsers();
			Assert.notNull(users);
			Assert.notEmpty(users);
			for (User u : users) {
				logger.info(u.toString());
			}
		}

		{
			System.out.println();
			logger.warn("trying to delete a user.");
			List<User> users = dao.getAllUsers();
			User u = users.get(0);
			Long id = u.getId();
			dao.deleteUser(u);
			Assert.isNull(dao.getUserById(id));
			users = dao.getAllUsers();
			boolean success = true;
			for (User u2 : users) {
				if (u2.getId().equals(id)) {
					success = false;
					break;
				}
			}
			Assert.isTrue(success);
		}
	}

}
