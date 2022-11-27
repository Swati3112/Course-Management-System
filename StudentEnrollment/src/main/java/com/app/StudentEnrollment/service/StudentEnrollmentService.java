package com.app.StudentEnrollment.service;

import com.app.StudentEnrollment.model.entity.Student;
import com.app.StudentEnrollment.payload.Request;

public interface StudentEnrollmentService {

	public Student enrollForCourse(Request req);

	public Student enrollForAssignment(Request req);

	public Student getEnrolledStudentDetails(int id);

}
