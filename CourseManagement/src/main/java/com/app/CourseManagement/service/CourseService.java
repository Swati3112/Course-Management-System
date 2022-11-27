package com.app.CourseManagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.CourseManagement.model.entity.Course;

public interface CourseService {

	public List<Course> findAllCourse();

	public Course createCourse(Course c);

	public void deleteCourseByID(int id);

	public Course getCourseByID(int id);

	public Course updateCourseByID(int id, Course course);

}
