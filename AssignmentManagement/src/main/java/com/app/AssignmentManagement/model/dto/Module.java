package com.app.AssignmentManagement.model.dto;

public class Module {

	private int id;
	private String title;

	private Course course;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
