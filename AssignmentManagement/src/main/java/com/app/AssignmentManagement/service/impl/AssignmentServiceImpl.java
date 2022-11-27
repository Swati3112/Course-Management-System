package com.app.AssignmentManagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.AssignmentManagement.exception.ExceptionOccured;
import com.app.AssignmentManagement.model.dto.Course;
import com.app.AssignmentManagement.model.entity.Assignment;
import com.app.AssignmentManagement.model.repository.AssignmentRepository;
import com.app.AssignmentManagement.service.AssignmentService;
import com.app.AssignmentManagement.util.Util;


@Service
public class AssignmentServiceImpl implements AssignmentService{

	@Autowired
	private AssignmentRepository assignmentRepo;
	

	@Override
	public Assignment createAssignment(Assignment s) {
		Course c1= Util.getCourseDetails(s.getCourse_id());
        String username= Util.getUserName();
		if (!username.equals(c1.getInstructor_name()))
			throw new ExceptionOccured("You can not create this assignment as it is created by some other instructor", HttpStatus.UNAUTHORIZED);
		s.setCreated(new Date());
		s.setCreatedBy(username);
		return assignmentRepo.save(s);

	}

	@Override
	public void deleteAssignmentByID(int id) {
		
		Assignment c = assignmentRepo.findById(id)
				.orElseThrow(() -> new ExceptionOccured("Assignment doesn't exsists by this id", HttpStatus.NOT_FOUND));
		Course c1= Util.getCourseDetails(c.getCourse_id());
		String username= Util.getUserName();
		if (!username.equals(c1.getInstructor_name()))
			throw new ExceptionOccured("You can not delete this assignment as it is created by some other instructor", HttpStatus.UNAUTHORIZED);

		assignmentRepo.delete(c);
	}

	@Override
	public Assignment getAssignmentByID(int id) {
		Assignment s = assignmentRepo.findById(id)
				.orElseThrow(() -> new ExceptionOccured("Assignment doesn't exsists by this id", HttpStatus.NOT_FOUND));
		Course c1= Util.getCourseDetails(s.getCourse_id());
		String username= Util.getUserName();
		if (!username.equals(c1.getInstructor_name()))
			throw new ExceptionOccured("You are not authorized to access this assignment as it is created by some other instructor", HttpStatus.UNAUTHORIZED);

		return s;
	}

	@Override
	public Assignment updateAssignmentByID(int id, Assignment assignment) {
		Course c1= Util.getCourseDetails(assignment.getCourse_id());
		String username= Util.getUserName();
		
		Assignment getAssignment = assignmentRepo.findById(id)
				.orElseThrow(() -> new ExceptionOccured("Assignment doesn't exsists by this id", HttpStatus.NOT_FOUND));
		if (!username.equals(c1.getInstructor_name()))
			throw new ExceptionOccured("You can not update this assignment as it is created by someone else", HttpStatus.UNAUTHORIZED);
		
		if (getAssignment != null) {
			getAssignment.setModified(new Date());
			getAssignment.setTitle(assignment.getTitle());
			getAssignment.setCourse_id(assignment.getCourse_id());
			assignmentRepo.save(getAssignment);
		}
		return getAssignment;

	}

	@Override
	public List<Assignment> findAllAssignment() {
		return assignmentRepo.findAllByUserName(Util.getUserName());
	}
}
