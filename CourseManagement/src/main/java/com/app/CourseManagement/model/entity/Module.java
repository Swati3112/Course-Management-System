package com.app.CourseManagement.model.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity
public class Module {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String title;
	/*
	 * @ManyToOne()
	 * 
	 * @JoinColumn(name = "course_id", updatable = true, insertable = true) private
	 * Course course;
	 */
	//private int course_id;
	
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
	public Module() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * public Course getCourse() { return course; } public void setCourse(Course
	 * course) { this.course = course; }
	 */
	/*
	 * public int getCourse_id() { return course_id; } public void setCourse_id(int
	 * course_id) { this.course_id = course_id; }
	 */
	
}
