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
	public void delete(Task task) {
		sessionFactory.getCurrentSession().delete(task);
	}

	@Override
	public void delete(TaskList list) {
		sessionFactory.getCurrentSession().delete(list);
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List<User> result = sessionFactory.getCurrentSession()
				.createQuery("from User u left join fetch u.taskLists").list();
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

	@Transactional(readOnly = true)
	@Override
	public User getUserById(Long id) {
		String hql = "select distinct u from User as u left join fetch u.taskLists as ls where u.id = :id";
		return (User) sessionFactory.getCurrentSession().createQuery(hql)
				.setParameter("id", id).uniqueResult();
	}

	@Override
	public Task save(Task task) {
		sessionFactory.getCurrentSession().save(task);
		return task;
		// TODO does id populate correctly?
	}

	@Override
	public TaskList save(TaskList list) {
		sessionFactory.getCurrentSession().save(list);
		return list;
		// TODO does id populate correctly?
	}

	@Transactional
	@Override
	public User save(User user) {
		if (user != null) {
			sessionFactory.getCurrentSession().save(user);
		}
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

	@Override
	public User update(User user) {
		sessionFactory.getCurrentSession().update(user);
		return user;
	}

	@Override
	public Task update(Task task) {
		sessionFactory.getCurrentSession().update(task);
		return task;
	}

	@Override
	public TaskList update(TaskList list) {
		sessionFactory.getCurrentSession().update(list);
		return list;
	}
}
