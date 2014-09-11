package com.easoncxz.yntdl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Task {

	@ManyToOne
	private TaskList containingList;

	@Column
	private String contents;

	@Column
	private Date dueDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private Date lastModifiedDate;

	@ManyToOne
	private User owner;

	@Column
	private String title;

	public TaskList getContainingList() {
		return containingList;
	}

	public String getContents() {
		return contents;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public long getId() {
		return id;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public User getOwner() {
		return owner;
	}

	public String getTitle() {
		return title;
	}

	public void setContainingList(TaskList containingList) {
		this.containingList = containingList;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
