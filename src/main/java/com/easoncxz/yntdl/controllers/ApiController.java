package com.easoncxz.yntdl.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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

	@Autowired
	private Service service;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public Users getAllUsers() {
		return new Users(service.getAllUsers());
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public String createUser(@RequestBody User u) {
		Logger logger = LoggerFactory.getLogger(ApiController.class);
		logger.info("The user received from the POST has name: " + u.getName());
		logger.info("He has: " + u.getTaskLists().size()
				+ " task lists, each of which contains these number of tasks:");
		for (TaskList l : u.getTaskLists()) {
			logger.info(l.getTasks().size() + " tasks");
		}
		service.save(u);
		return "redirect:/api/users";
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

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

}
