package com.easoncxz.yntdl.service;

import static com.easoncxz.yntdl.util.MyUtils.unmarshalledUserFixer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.easoncxz.yntdl.domain.User;
import com.easoncxz.yntdl.persistence.Dao;

@org.springframework.stereotype.Service("serverService")
public class ServiceImpl implements Service {
	
	@Autowired
	private Dao dao;

	@Override
	public void delete(String token, User user) {
		unmarshalledUserFixer(user);
		dao.delete(user);
	}

	@Override
	public List<User> getAllUsers(String token) {
		return dao.getAllUsers();
	}

	@Override
	public User getUserById(String token, Long id) {
		return dao.getUserById(id);
	}

	@Override
	public User save(String token, User user) {
		unmarshalledUserFixer(user);
		dao.save(user);
		return user;
	}

	@Override
	public User update(String token, User user) {
		unmarshalledUserFixer(user);
		dao.update(user);
		return user;
	}

}
