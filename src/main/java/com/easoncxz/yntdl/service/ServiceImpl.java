package com.easoncxz.yntdl.service;

import static com.easoncxz.yntdl.util.MyUtils.cleanUser;
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
		cleanUser(user);
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
		cleanUser(user);
		dao.save(user);
	}

	@Override
	public void update(User user) {
		cleanUser(user);
		dao.update(user);
	}

}
