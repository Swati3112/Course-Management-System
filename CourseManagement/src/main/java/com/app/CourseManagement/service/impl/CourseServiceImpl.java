package com.app.CourseManagement.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.app.CourseManagement.exception.ExceptionOccured;
import com.app.CourseManagement.model.entity.Course;
import com.app.CourseManagement.model.repository.CourseRepository;
import com.app.CourseManagement.service.CourseService;
import com.app.CourseManagement.util.Util;

@Service
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseRepository cr;
	
	@Autowired 
	private Util util;
	
	@Override
	public Course createCourse(Course c) {
		Course cget= cr.findByTitle(c.getTitle());
		if(cget!=null) throw new ExceptionOccured("Course already exists with this name", HttpStatus.BAD_REQUEST);
		String usename= util.getUserName();
		c.setInstructor_name(usename);
		c.setCreated(new Date());
		return cr.save(c);
		
	}
	@Override
	public void deleteCourseByID(int id) {
		Course c= cr.findById(id).orElseThrow(()-> new ExceptionOccured("Course doesn't exists with this id", HttpStatus.NOT_FOUND));
		if(!c.getInstructor_name().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to delete this course as it is not created by you", HttpStatus.UNAUTHORIZED);
		cr.delete(c);
	}
	
	@Override
	public Course getCourseByID(int id) {
		Course c= cr.findById(id).orElseThrow(()-> new ExceptionOccured("Course doesn't exists with this id", HttpStatus.NOT_FOUND));
		if(!c.getInstructor_name().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to access this course as it is not created by you", HttpStatus.UNAUTHORIZED);
		return c;
	}
	
	@Override
	public Course updateCourseByID(int id, Course course) {
		Course getCourse= cr.findById(id).orElseThrow(()->new ExceptionOccured("Course doesn't exists with this id", HttpStatus.NOT_FOUND));
		if(!getCourse.getInstructor_name().equals(Util.getUserName())) throw new ExceptionOccured("You are not authorized to update this course as it is not created by you", HttpStatus.UNAUTHORIZED);
		if(getCourse!=null) {
			getCourse.setModified(new Date());
			getCourse.setModules(course.getModules());
			getCourse.setTitle(course.getTitle());
			cr.save(getCourse);
		}
		return getCourse;
		
	}
	@Override
	public List<Course> findAllCourse(){
		return cr.findAllByUserName(Util.getUserName());
	}
}
