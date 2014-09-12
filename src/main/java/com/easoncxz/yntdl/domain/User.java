package com.easoncxz.yntdl.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
			orphanRemoval = true)
	private Set<TaskList> taskLists;

	public void addTaskList(TaskList l) {
		if (l != null) {
			if (this.taskLists == null) {
				this.taskLists = new HashSet<TaskList>();
			}
			this.taskLists.add(l);
			l.setOwner(this);
		}
	}

	public void deleteTaskList(TaskList l) {
		if (l != null) {
			if (this.taskLists == null) {
				return;
			}
			this.taskLists.remove(l);
			l.setOwner(null);
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<TaskList> getTaskLists() {
		return new ArrayList<TaskList>(taskLists);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTaskLists(List<TaskList> taskLists) {
		this.taskLists = new HashSet<TaskList>(taskLists);
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
