package com.app.StudentEnrollment.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class Student {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	private String userName;

	@NotBlank
	private String enrolledBy;

	@NotNull
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
	@JoinTable(name = "student_enroll", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "en_id"))
	private Set<EnrollmentName> enrollments = new HashSet<>();

	public Student() {
	}

	public Student(@NotBlank String userName, @NotBlank String enrolledBy, @NotNull Set<EnrollmentName> enrollments) {
		super();
		this.userName = userName;
		this.enrolledBy = enrolledBy;
		this.enrollments = enrollments;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public Set<EnrollmentName> getEnrollments() {
		return enrollments;
	}

	public void setEnrollments(Set<EnrollmentName> enrollments) {
		this.enrollments = enrollments;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEnrolledBy() {
		return enrolledBy;
	}

	public void setEnrolledBy(String enrolledBy) {
		this.enrolledBy = enrolledBy;
	}

}
