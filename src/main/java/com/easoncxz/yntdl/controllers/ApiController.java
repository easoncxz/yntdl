package com.easoncxz.yntdl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.service.Service;

@Controller("apiController")
@RequestMapping(value = "/api")
public class ApiController {

	@Autowired
	private Service service;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllUsers() {
		return service.getAllUsers();
	}
	
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	@ResponseBody
	public User getUserById(@PathVariable Long id) {
		return service.getUserById(id);
	}

}
