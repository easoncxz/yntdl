package com.easoncxz.yntdl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service {
	
	@Autowired
	private Dao dao;

	@Override
	public void delete(User user) {
		dao.delete(user);
	}

	@Override
	public List<User> getAllUsers() {
		return dao.getAllUsers();
	}

	@Override
	public User getUserById(Long id) {
		return dao.getUserById(id);
	}

	@Override
	public void save(User user) {
		dao.save(user);
	}

	@Override
	public void update(User user) {
		dao.update(user);
	}

}
