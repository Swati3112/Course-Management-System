package com.app.CourseManagement.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.CourseManagement.model.entity.Course;
import com.app.CourseManagement.service.impl.CourseServiceImpl;

@RestController
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	CourseServiceImpl courseService;
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR') or hasRole('ROLE_STUDENT')")
	@GetMapping("/all")
	public Iterable<Course> findAllCourses() {
		return courseService.findAllCourse(); 
	}
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR') or hasRole('ROLE_STUDENT')")
	@GetMapping("/{courseId}")
	public Course findCourseById(@PathVariable("courseId") int id) {
		return courseService.getCourseByID(id);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/createCourse")
	public Course createCourse(@RequestBody Course course) {
		return courseService.createCourse(course);
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("/{courseId}")
	public String deleteCourse(@PathVariable("courseId") int id) {
		courseService.deleteCourseByID(id);
		return "Course with"+ id+ " has been deleted";
	}
	
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateCourse(@PathVariable(value = "id") int id, @RequestBody Course req){
		return ResponseEntity.ok(courseService.updateCourseByID(id, req));
		
	}

}
