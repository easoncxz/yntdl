package com.easoncxz.yntdl.persistence;

import java.util.List;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

/**
 * Provides CRUD persistence for domain objects.
 */
public interface Dao {

	void deleteUser(User u); // D

	List<User> getAllUsers(); // R

	User getUserById(long id); // R

	/**
	 * Both persists newly-created domain objects, and updates domain objects
	 * that have already been persisted before. I.e., this provides both
	 * "Create" and "Update" functionality in "CRUD".
	 */
	void saveUser(User user); // C, U

	/**
	 * Searches for user by their human-readable name
	 *
	 * @return a set of search results
	 */
	List<User> searchForUserByName(); // R

	// ------

	void deleteTaskList(TaskList list); // D

	/**
	 * This API shouldn't be necessary, since the User domain object should
	 * maintain a set of task lists as part of its state.
	 *
	 * @param user
	 * @return
	 */
	List<TaskList> getOwnedTaskLists(User user); // R

	void saveTaskList(TaskList list); // C, U

	// ------

	void deleteTask(Task task); // D

	/**
	 * Similar to {@link #getOwnedTaskLists(User)}, this API shouldn't be
	 * necessary.
	 *
	 * @param list
	 * @return
	 */
	List<Task> getTasksInList(TaskList list); // R

	void saveTask(Task task); // C, U

}
