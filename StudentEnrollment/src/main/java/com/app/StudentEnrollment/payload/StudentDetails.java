package com.app.StudentEnrollment.payload;

public class StudentDetails {
	
	private int student_id;
	private String userName;
	public int getStudent_id() {
		return student_id;
	}
	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public StudentDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StudentDetails(int student_id, String userName) {
		super();
		this.student_id = student_id;
		this.userName = userName;
	}
	
	
	
	
	

}
