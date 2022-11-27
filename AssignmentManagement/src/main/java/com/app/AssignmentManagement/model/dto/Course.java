package com.app.AssignmentManagement.model.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class Course {
	
	private int id;
	private String title;
	private Date created;
	private Date modified;
	
	private String instructor_name;
	private List<Module> modules;
	
	
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		//DateTime date = new DateTime(created);
		this.created = created; 
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public String getInstructor_name() {
		return instructor_name;
	}
	public void setInstructor_name(String instructor_name) {
		this.instructor_name = instructor_name;
	}
	public Course(String title, Date created, Date modified, String instructor_name, List<Module> modules) {
		super();
		this.title = title;
		this.created = created;
		this.modified = modified;
		this.instructor_name = instructor_name;
		this.modules = modules;
	}
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
