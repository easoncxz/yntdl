package com.easoncxz.yntdl.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TASK_LIST")
public class TaskList {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	@JoinColumn(name = "USER_ID")
	private User owner;

	@OneToMany(
			mappedBy = "containingList",
			targetEntity = Task.class,
			cascade = CascadeType.ALL,
			orphanRemoval = true,
			fetch = FetchType.EAGER)
	private Set<Task> tasks;

	public void addTask(Task task) {
		if (task != null) {
			if (this.tasks == null) {
				this.tasks = new HashSet<Task>();
			}
			this.tasks.add(task);
			task.setContainingList(this);
		}
	}

	public void deleteTask(Task task) {
		if (task != null) {
			if (this.tasks == null) {
				return;
			}
			this.tasks.remove(task);
			task.setContainingList(null);
		}
	}

	/**
	 * @param other
	 * @return true iff persistence object id is the same
	 */
	public boolean equals(TaskList other) {
		if (other == null) {
			return false;
		} else if (this.id == null) {
			return false;
		} else {
			return this.id.equals(other.id);
		}
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	User getOwner() {
		return owner;
	}
	
	public List<Task> getTasks() {
		return new ArrayList<Task>(tasks);
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	void setOwner(User owner) {
		this.owner = owner;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = new HashSet<Task>(tasks);
	}

}
