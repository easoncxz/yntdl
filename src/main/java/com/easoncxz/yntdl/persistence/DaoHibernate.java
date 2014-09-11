package com.easoncxz.yntdl.persistence;

import java.util.List;

import javax.annotation.Resource;

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
		sessionFactory.getCurrentSession().delete(task);
	}

	@Override
	public void deleteTaskList(TaskList list) {
		sessionFactory.getCurrentSession().delete(list);
	}

	@Override
	public void deleteUser(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List<User> result = sessionFactory.getCurrentSession()
				.createQuery("from User u").list();
		return result;
	}

	@Deprecated
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskList> getOwnedTaskLists(User user) {
		String hql = "select distinct list from TaskList as list inner join list.owner where list.owner = :user";
		List<TaskList> result = sessionFactory.getCurrentSession()
				.createQuery(hql).setParameter("user", user).list();
		return result;
	}

	@Deprecated
	@Override
	public List<Task> getTasksInList(TaskList list) {
		return null;
	}

	@Override
	public User getUserById(Long id) {
		String hql = "select distinct u from User as u where u.id = :id";
		return (User) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("id", id).uniqueResult();
	}

	@Override
	public Task saveTask(Task task) {
		sessionFactory.getCurrentSession().saveOrUpdate(task);
		return task;
		// TODO does id populate correctly?
	}

	@Override
	public TaskList saveTaskList(TaskList list) {
		sessionFactory.getCurrentSession().saveOrUpdate(list);
		return list;
		// TODO does id populate correctly?
	}

	@Override
	public User saveUser(User user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		return user;
		// TODO does id populate correctly?
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> searchForUserByName(String name) {
		String hql = "from User u where u.name = :name";
		return sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("name", name).list();
	}

	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
