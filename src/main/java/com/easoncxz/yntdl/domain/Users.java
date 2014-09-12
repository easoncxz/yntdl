package com.easoncxz.yntdl.domain;

import java.util.List;

public class Users {

	private List<User> users;

	public Users() {
	}

	public Users(List<User> contacts) {
		this.users = contacts;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> contacts) {
		this.users = contacts;
	}

}