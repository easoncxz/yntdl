package com.easoncxz.yntdl.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

@Controller(value = "/api")
public class ApiController {
	
	@Autowired
	private Dao dao;
	
	@RequestMapping
	@ResponseBody
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

}
