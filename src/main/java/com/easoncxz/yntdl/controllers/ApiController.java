package com.easoncxz.yntdl.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.easoncxz.yntdl.persistence.Dao;

@Controller(value = "/api")
public class ApiController {
	
	private Dao dao;
	
	public List<User> getAllUsers() {
		
	}

}
