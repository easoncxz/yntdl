package com.easoncxz.yntdl;

import static com.easoncxz.yntdl.util.MyUtils.dumpUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;

public class RestHttpTaskListClient {

	private static final String BASE_URL = "http://localhost:8080/yntdl/api";
	private static final String URL_GET_ALL_USERS = BASE_URL + "/users";
	private static final String URL_CREATE_USER = BASE_URL + "/users/";

	// private static final String URL_ ;
	// private static final String URL_ ;
	// private static final String URL_;

	public static void main(String[] args) {
		GenericXmlApplicationContext context = new GenericXmlApplicationContext();
		context.load("client-context.xml");
		context.refresh();
		RestTemplate template = context.getBean("restTemplate",
				RestTemplate.class);
		Logger logger = LoggerFactory.getLogger(RestHttpTaskListClient.class);
		Assert.notNull(template);

		User u = new User();
		TaskList l = new TaskList();
		Task t = new Task();
		t.setTitle("TaskFromClient");
		l.addTask(t);
		l.setName("ListFromClient");
		u.addTaskList(l);
		u.setName("UserFromClient");

		logger.info("Now about to execute POST from client");
		dumpUser(logger, u);
		u = template.postForObject(URL_CREATE_USER, u, User.class);
		logger.info("Now the POST returned");
		dumpUser(logger, u);

		{
			logger.info("Now we're about to retrieve all users");
			@SuppressWarnings("unchecked")
			Users userList = template.getForObject(URL_GET_ALL_USERS,
					Users.class);
			logger.info("the 'userList' really is a: "
					+ userList.getClass().getName());
			for (User user : userList.getUsers()) {
				dumpUser(logger, user);
			}
			logger.info("All users retrieved. ^");
		}
		
	}

}
