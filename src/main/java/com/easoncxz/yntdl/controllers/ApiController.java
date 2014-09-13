package com.easoncxz.yntdl.controllers;

import static com.easoncxz.yntdl.util.MyUtils.dumpUser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.domain.Users;
import com.easoncxz.yntdl.service.Service;

@Controller("apiController")
@RequestMapping(value = "/api")
public class ApiController {

	private Service service;

	@Autowired
	public void setService(Service serverService) {
		this.service = serverService;
	}

	private Logger logger = LoggerFactory.getLogger(ApiController.class);

	@RequestMapping(value = "/users/", method = RequestMethod.POST)
	@ResponseBody
	public User createUser(@RequestBody User u) {
		logger.info("Will now dump POSTed user:");
		dumpUser(logger, u);

		User result = service.save("", u);

		logger.info("After the service.save call, here is the user again:");
		dumpUser(logger, result);
		return result;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public Users getAllUsers(
			@RequestHeader(value = "Authorization") String token) {
		logger.info("The Authorization header is: " + token);
		return new Users(service.getAllUsers(""));
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable Long id) {
		return service.getUserById("", id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(@RequestBody User u, @PathVariable Long id) {
		User result = service.update("", u);
		return result;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id) {
		User u = service.getUserById("", id);
		if (u != null) {
			service.delete("", u);
		}
		return "redirect:/users/";
	}

	@RequestMapping(value = "/users/john", method = RequestMethod.GET)
	@ResponseBody
	public User getJohn() {
		User u = new User();
		u.setName("John");

		TaskList l = new TaskList();
		l.setName("SomeList");

		Task t = new Task();
		t.setTitle("TaskTitleHere");
		l.addTask(t);

		t = new Task();
		t.setTitle("AnotherTask");
		l.addTask(t);

		u.addTaskList(l);

		l = new TaskList();
		l.addTask(new Task());
		u.addTaskList(l);
		return u;
	}

}
