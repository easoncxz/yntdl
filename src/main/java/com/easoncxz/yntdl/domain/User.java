package com.easoncxz.yntdl.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(
			mappedBy = "owner",
			targetEntity = TaskList.class,
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER)
	private List<TaskList> taskLists;

	public void addTaskList(TaskList l) {
		if (this.taskLists == null) {
			this.taskLists = new ArrayList<TaskList>();
		}
		this.taskLists.add(l);
		if (l != null) {
			l.setOwner(this);
		}
	}

	public void deleteTaskList(TaskList l) {
		if (this.taskLists == null) {
			return;
		}
		this.taskLists.remove(l);
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<TaskList> getTaskLists() {
		return taskLists;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTaskLists(List<TaskList> taskLists) {
		this.taskLists = taskLists;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
