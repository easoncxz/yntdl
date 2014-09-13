package com.easoncxz.yntdl.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task implements Comparable<Task> {

	@ManyToOne
	@JoinColumn(name = "TASK_LIST_ID")
	private TaskList containingList;

	@Column(name = "CONTENTS")
	private String contents;

	@Column(name = "DUE_DATE")
	private Date dueDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "LAST_MODIFIED_DATE")
	private Date lastModifiedDate;

	@Column(name = "TITLE")
	private String title;

	/**
	 * @param other
	 * @return true iff persistence object id is the same
	 */
	public boolean equals(Task other) {
		if (other == null) {
			return false;
		} else if (this.id == null) {
			return false;
		} else {
			return this.id.equals(other.id);
		}
	}

	TaskList getContainingList() {
		return containingList;
	}

	public String getContents() {
		return contents;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public Long getId() {
		return id;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public String getTitle() {
		return title;
	}

	void setContainingList(TaskList containingList) {
		this.containingList = containingList;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int compareTo(Task o) {
		return this.id.compareTo(o.id);
	}

}
