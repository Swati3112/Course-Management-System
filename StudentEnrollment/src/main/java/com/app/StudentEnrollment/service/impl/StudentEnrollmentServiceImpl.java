package com.app.StudentEnrollment.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.app.StudentEnrollment.exception.ExceptionOccured;
import com.app.StudentEnrollment.model.dto.Assignment;
import com.app.StudentEnrollment.model.dto.Course;
import com.app.StudentEnrollment.model.entity.EnrollmentName;
import com.app.StudentEnrollment.model.entity.Student;
import com.app.StudentEnrollment.model.repository.EnrollmentNameRepo;
import com.app.StudentEnrollment.model.repository.StudentRepo;
import com.app.StudentEnrollment.payload.Request;
import com.app.StudentEnrollment.service.StudentEnrollmentService;
import com.app.StudentEnrollment.util.Util;

@Service
public class StudentEnrollmentServiceImpl implements StudentEnrollmentService{
	
	@Autowired
	private StudentRepo studentRepo;
	
	@Autowired 
	private EnrollmentNameRepo enrollmentRepo;
	
	@Override
	public Student enrollForCourse(Request req){
		Set<EnrollmentName> elist= new HashSet();
		
		
		for(Integer id: req.getCourseAssignmentIDs()){
			//enrollmentRepo.save(new EnrollmentName("course",s, new Date(), null));
			Course getC= Util.getCourseDetails(id);
			if(!getC.getInstructor_name().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to enroll course with id "+ getC.getId()+" as it is not created by you", HttpStatus.UNAUTHORIZED);
			elist.add(new EnrollmentName("course",getC.getTitle(), new Date(), null, id));
		}
		
		Student student= new Student(req.getStudentUserName(), Util.getUserName(), elist);
		return studentRepo.save(student);
	}
	
	@Override
	public Student enrollForAssignment(Request req){
		Set<EnrollmentName> elist= new HashSet();
		for(Integer id: req.getCourseAssignmentIDs()){
			Assignment getC= Util.getAssignmentDetails(id);
			if(!getC.getCreatedBy().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to enroll for assignment with id"+ getC.getId()+" as it is not created by you", HttpStatus.UNAUTHORIZED);
			elist.add(new EnrollmentName("course",getC.getTitle(), new Date(), null, id));
		}
		Student student= new Student(req.getStudentUserName(), Util.getUserName(), elist);
		
		return studentRepo.save(student);
		
	}
	
	@Override
	public Student getEnrolledStudentDetails(int id ){
		Student student= studentRepo.findById(id).orElseThrow(()->new ExceptionOccured("No enrollment found by this enrollment ID", HttpStatus.NOT_FOUND));
		//if(en.getType().equalsIgnoreCase("assignment")) throw new NameNotFound("No enrollment found with this Course ID");
		if(!student.getEnrolledBy().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to access the student details by this enrollment as it is not created by you", HttpStatus.UNAUTHORIZED);
		
		
		return student;
		
	}
	
	

}
