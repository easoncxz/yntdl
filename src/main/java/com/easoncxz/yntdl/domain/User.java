package com.easoncxz.yntdl.domain;

import java.util.List;

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

	@Column(name = "NAME")
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@OneToMany(
			mappedBy = "owner",
			targetEntity = TaskList.class,
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	private List<TaskList> taskLists;

	public String getName() {
		return name;
	}

	public Long getId() {
		return id;
	}

	public List<TaskList> getTaskLists() {
		return taskLists;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaskLists(List<TaskList> taskLists) {
		this.taskLists = taskLists;
	}

	@Override
	public String toString() {
		return this.getName();
	}

}
