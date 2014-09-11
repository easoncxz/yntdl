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

public class DaoJdbcTemplate implements Dao {

	public DaoJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.template = namedParameterJdbcTemplate;
	}

	private NamedParameterJdbcTemplate template;

	private static Map<String, Object> userUnmapper(User user) {
		Map<String, Object> result = new TreeMap<String, Object>();
		result.put("humanReadableName", user.getHumanReadableName());
		return result;
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setId(rs.getLong("id"));
			user.setHumanReadableName(rs.getString("humanReadableName"));
			return user;
		}

	}
	
	@Override
	public void deleteUser(User u) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<User> getAllUsers() {
		String sql = "select id, humanReadableName from User;";
		return this.template.query(sql, new UserMapper());
	}

	@Override
	public User getUserById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveUser(User user) {
		if (user.getId() == null) {
			// we've got a new user
			String sql = "insert into User (humanReadableName) values (:humanReadableName);";
			this.template.update(sql, userUnmapper(user));
		}
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
