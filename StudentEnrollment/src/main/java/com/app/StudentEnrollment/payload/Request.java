package com.app.StudentEnrollment.payload;

import java.util.List;

import com.app.StudentEnrollment.model.dto.Course;

import lombok.Data;


public class Request {
    
    private List<Integer> courseAssignmentIDs;
    
    private String studentUserName;

	public List<Integer> getCourseAssignmentIDs() {
		return courseAssignmentIDs;
	}

	public void setCourseAssignmentIDs(List<Integer> courseIDs) {
		this.courseAssignmentIDs = courseIDs;
	}

	public String getStudentUserName() {
		return studentUserName;
	}

	public void setStudentUserName(String studentUserName) {
		this.studentUserName = studentUserName;
	}

	public Request(List<Integer> courseAssignmentIDs, String studentUserName) {
		super();
		this.courseAssignmentIDs = courseAssignmentIDs;
		this.studentUserName = studentUserName;
	}

	public Request() {
		super();
		// TODO Auto-generated constructor stub
	}

	

    
    
    

}
