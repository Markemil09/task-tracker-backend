package com.em.tasktracker.model;


import javax.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "text")
	private String text;

	@Column(name = "sched")
	private String sched;
	
	public Task() {
		super();
	}
	
	public Task(long id, String taskName, String descriptionSched) {
		super();
		this.id = id;
		this.text = text;
		this.sched = sched;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSched() {
		return sched;
	}
	public void setSched(String sched) {
		this.sched = sched;
	}
	

}
