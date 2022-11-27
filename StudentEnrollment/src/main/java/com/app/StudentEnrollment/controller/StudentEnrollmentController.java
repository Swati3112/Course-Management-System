package com.app.StudentEnrollment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.StudentEnrollment.model.entity.Student;
import com.app.StudentEnrollment.payload.Request;
import com.app.StudentEnrollment.service.impl.StudentEnrollmentServiceImpl;

@RestController
@RequestMapping("/enroll")
public class StudentEnrollmentController {
	
	@Autowired
	StudentEnrollmentServiceImpl service;
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/course")
	public ResponseEntity<Object> enrollCourse(@RequestBody Request req){
		
		return ResponseEntity.ok(service.enrollForCourse(req));
		
	}
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/assignment")
	public ResponseEntity<Object> enrollAssignment(@RequestBody Request req){
		
		return ResponseEntity.ok(service.enrollForAssignment(req));
		
	}
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@GetMapping("/student-details-by-enrollment-no/{id}")
	public ResponseEntity<Object> getEnrolledCourseStudentDetails(@PathVariable(value = "id")int id ){
		
		return ResponseEntity.ok(service.getEnrolledStudentDetails(id));
		
	}
	
	

}
