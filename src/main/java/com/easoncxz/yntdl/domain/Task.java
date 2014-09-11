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

}
