package com.app.StudentEnrollment.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.StudentEnrollment.model.entity.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer>{
	@Query(value="select * from  \"student\" s where s.\"id\" IN( select se.\"student_id\" from \"student_enroll\" se where se.\"en_id\"=:id)", nativeQuery = true)
	public List<Student> findByEnrollments(@Param("id") int id);
}
