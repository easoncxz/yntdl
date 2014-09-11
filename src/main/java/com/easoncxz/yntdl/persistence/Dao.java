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

	/**
	 * @return list of users in order of id
	 */
	List<User> getAllUsers(); // R

	/**
	 * @param id
	 * @return null if no such user exists
	 */
	User getUserById(long id); // R

	/**
	 * Both persists newly-created domain objects, and updates domain objects
	 * that have already been persisted before. I.e., this provides both
	 * "Create" and "Update" functionality in "CRUD".
	 * 
	 * @param user
	 * @return the newly-saved user. (It'll have its id populated.)
	 */
	User saveUser(User user); // C, U

	/**
	 * Searches for user by their human-readable name
	 *
	 * @return a non-null list of search results, ordered by id
	 */
	List<User> searchForUserByName(); // R

	// ------

	void deleteTaskList(TaskList list); // D

	/**
	 * This API shouldn't be necessary, since the User domain object should
	 * maintain a list of task lists as part of its state.
	 *
	 * @param user
	 * @return a non-null list of task lists, ordered by persisted ordering
	 */
	List<TaskList> getOwnedTaskLists(User user); // R

	void saveTaskList(TaskList taskList); // C, U

	// ------

	void deleteTask(Task task); // D

	/**
	 * Similar to {@link #getOwnedTaskLists(User)}, this API shouldn't be
	 * necessary.
	 *
	 * @param taskList
	 * @return a non-null list of tasks, ordered by persisted ordering
	 */
	List<Task> getTasksInList(TaskList taskList); // R

	void saveTask(Task task); // C, U

}
