package com.easoncxz.yntdl.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class User {

	@Column
	private String humanReadableName;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@OneToMany
	private Set<TaskList> taskLists;

	public String getHumanReadableName() {
		return humanReadableName;
	}

	public Long getId() {
		return id;
	}

	public Set<TaskList> getTaskLists() {
		return taskLists;
	}

	public void setHumanReadableName(String humanReadableName) {
		this.humanReadableName = humanReadableName;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTaskLists(Set<TaskList> taskLists) {
		this.taskLists = taskLists;
	}

}
