package com.app.StudentEnrollment.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class EnrollmentName {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private int course_assigment_id;
	
	@NotBlank
	private String type;
	
	@NotBlank
	private String title;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;
	
	public EnrollmentName() {
		
	}

	public EnrollmentName(String type, String title, Date created, Date modified, int course_assigment_id) {
		
		
		this.type = type;
		this.title = title;
		this.created = created;
		this.modified = modified;
		this.course_assigment_id=course_assigment_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public int getCourse_assigment_id() {
		return course_assigment_id;
	}

	public void setCourse_assigment_id(int course_assigment_id) {
		this.course_assigment_id = course_assigment_id;
	}
	
	

}
