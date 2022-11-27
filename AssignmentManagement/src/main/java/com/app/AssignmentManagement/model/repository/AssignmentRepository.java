package com.app.AssignmentManagement.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.AssignmentManagement.model.entity.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer>{
	
	
	@Query(value="select * from  \"assignment\" s where s.\"created_by\" =:name", nativeQuery = true)
	public List<Assignment> findAllByUserName(@Param("name") String name);

}
