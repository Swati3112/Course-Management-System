package com.app.AssignmentManagement.controller;

import java.util.List;

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

import com.app.AssignmentManagement.model.entity.Assignment;
import com.app.AssignmentManagement.service.impl.AssignmentServiceImpl;

@RequestMapping("/assignment")
@RestController
public class AssignmentController {
	
	@Autowired
	AssignmentServiceImpl assignmentService;
	
	@GetMapping("/all")
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	public List<Assignment> findAllCourses() {
		return assignmentService.findAllAssignment(); 
	}
	
	@GetMapping("{assignmentId}")
	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	public ResponseEntity<Object> findAssignmentById(@PathVariable("assignmentId") int id) {
		return ResponseEntity.ok(assignmentService.getAssignmentByID(id));
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@PostMapping("/createAssignment")
	public ResponseEntity<Object> createAssignment(@RequestBody Assignment ass) {
		return ResponseEntity.ok(assignmentService.createAssignment(ass));
	}

	@PreAuthorize("hasRole('ROLE_INSTRUCTOR')")
	@DeleteMapping("{assignmentId}")
	public String deleteAssignment(@PathVariable("assignmentId") int id) {
		
		assignmentService.deleteAssignmentByID(id);
		return "assignment with id "+ id+ " has been deleted successfully";
	}
		
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateAssignment(@PathVariable(value = "id") int id, @RequestBody Assignment req){
		return ResponseEntity.ok(assignmentService.updateAssignmentByID(id, req));
		
	}

}
