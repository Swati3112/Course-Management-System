package com.app.CourseManagement.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.CourseManagement.model.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer>{
	@Query(value="select * from  \"course\" s where s.\"instructor_name\" =:name", nativeQuery = true)
	public List<Course> findAllByUserName(@Param("name") String name);
	
	public Course findByTitle(String name);

}

