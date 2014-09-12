package com.easoncxz.yntdl.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.easoncxz.yntdl.domain.Task;
import com.easoncxz.yntdl.domain.TaskList;
import com.easoncxz.yntdl.domain.User;

public class DaoJdbcTemplate {

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("ID"));
			user.setName(rs.getString("HUMAN_READABLE_NAME"));
			return user;
		}

	}

	private static Map<String, Object> userUnmapper(User user) {
		Map<String, Object> result = new TreeMap<String, Object>();
		result.put("HUMAN_READABLE_NAME", user.getName());
		result.put("ID", user.getId());
		return result;
	}

	private NamedParameterJdbcTemplate template;

	public DaoJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.template = namedParameterJdbcTemplate;
	}

	
	public void delete(Task task) {
		// TODO Auto-generated method stub

	}

	
	public void delete(TaskList list) {
		// TODO Auto-generated method stub

	}

	
	public void delete(User u) {
		// TODO Auto-generated method stub

	}

	
	public List<User> getAllUsers() {
		String sql = "SELECT ID, HUMAN_READABLE_NAME FROM USER;";
		return this.template.query(sql, new UserMapper());
	}

	
	public List<TaskList> getOwnedTaskLists(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public List<Task> getTasksInList(TaskList list) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Task save(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TaskList save(TaskList list) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User save(User user) {
		if (user.getId() == null) {
			// we've got a new user
			String sql = "INSERT INTO USER (HUMAN_READABLE_NAME) VALUES (:HUMAN_READABLE_NAME);";
			this.template.update(sql, userUnmapper(user));
		}
		// TODO update auto-generated id into User object
		return user;
	}

	
	public List<User> searchForUserByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public User update(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public Task update(Task task) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public TaskList update(TaskList list) {
		// TODO Auto-generated method stub
		return null;
	}
}
