package com.easoncxz.yntdl.controllers;

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
	public User createUser(@RequestBody User u, @RequestHeader(
			value = "Authorization",
			required = true) String token) {
		User result = service.save(token, u);
		return result;
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public Users getAllUsers(@RequestHeader(
			value = "Authorization",
			required = true) String token) {
		return new Users(service.getAllUsers(token));
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable Long id, @RequestHeader(
			value = "Authorization",
			required = true) String token) {
		return service.getUserById(token, id);
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public User updateUser(
			@RequestBody User u,
			@PathVariable Long id,
			@RequestHeader(value = "Authorization", required = true) String token) {
		User result = service.update(token, u);
		return result;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
	public String deleteUser(@PathVariable Long id, @RequestHeader(
			value = "Authorization",
			required = true) String token) {
		User u = service.getUserById(token, id);
		if (u != null) {
			service.delete(token, u);
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
