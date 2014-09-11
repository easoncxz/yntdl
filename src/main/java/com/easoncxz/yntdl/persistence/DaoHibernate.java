package com.easoncxz.yntdl.persistence;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.classic.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

@Repository("daoHibernate")
@Transactional
public class DaoHibernate implements Dao {

	private SessionFactory sessionFactory;

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteTaskList(TaskList list) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List<User> result = sessionFactory.getCurrentSession()
				.createQuery("from User u").list();
		return result;
	}

	@Override
	public List<TaskList> getOwnedTaskLists(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Task> getTasksInList(TaskList list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public void saveTaskList(TaskList list) {
		// TODO Auto-generated method stub
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		Session s = sessionFactory.getCurrentSession();
		s.saveOrUpdate(user);
		return user;
	}

	@Override
	public List<User> searchForUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
