package com.app.StudentEnrollment.model.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.StudentEnrollment.model.entity.EnrollmentName;

@Repository
public interface EnrollmentNameRepo extends JpaRepository<EnrollmentName, Integer>{
	public Optional<EnrollmentName> findByTitle(String name);
	

}
