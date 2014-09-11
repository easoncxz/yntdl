package com.easoncxz.yntdl.persistence;

import java.util.List;
import java.util.Set;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class DaoHibernate implements Dao {

	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		return user;
	}

	@Override
	public List<User> searchForUserByName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTaskList(TaskList list) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<TaskList> getOwnedTaskLists(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTaskList(TaskList list) {
		// TODO Auto-generated method stub
	}

	@Override
	public void deleteTask(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Task> getTasksInList(TaskList list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveTask(Task task) {
		// TODO Auto-generated method stub
	}
}
