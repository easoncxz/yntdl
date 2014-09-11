package com.easoncxz.yntdl.persistence;

import java.util.List;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

/**
 * Provides CRUD persistence for domain objects.
 */
public interface Dao {

	void delete(Task task); // D

	void delete(TaskList list); // D

	void delete(User u); // D

	/**
	 * @return list of users in order of id
	 */
	List<User> getAllUsers(); // R

	/**
	 * This API shouldn't be necessary, since the User domain object should
	 * maintain a list of task lists as part of its state.
	 *
	 * @deprecated
	 * @param user
	 * @return a non-null list of task lists, ordered by persisted ordering
	 */
	List<TaskList> getOwnedTaskLists(User user); // R

	// ------

	/**
	 * Similar to {@link #getOwnedTaskLists(User)}, this API shouldn't be
	 * necessary.
	 *
	 * @deprecated
	 * @param taskList
	 * @return a non-null list of tasks, ordered by persisted ordering
	 */
	List<Task> getTasksInList(TaskList taskList); // R

	/**
	 * @param id
	 * @return null if no such user exists
	 */
	User getUserById(Long id); // R

	/**
	 * @param task
	 * @return the newly-saved Task, with its id filled-in.
	 */
	Task save(Task task); // C, U

	// ------

	/**
	 * @param taskList
	 * @return the newly-saved TaskList (with its id filled-in).
	 */
	TaskList save(TaskList taskList); // C, U

	/**
	 * Both persists newly-created domain objects, and updates domain objects
	 * that have already been persisted before. I.e., this provides both
	 * "Create" and "Update" functionality in "CRUD".
	 * 
	 * @param user
	 * @return the newly-saved user. (It'll have its id populated.)
	 */
	User save(User user); // C, U

	/**
	 * Searches for user by their human-readable name
	 *
	 * @return a non-null list of search results, ordered by id
	 */
	List<User> searchForUserByName(String name); // R

	User udpate(User user);

	Task update(Task task);

	TaskList update(TaskList list);

}
