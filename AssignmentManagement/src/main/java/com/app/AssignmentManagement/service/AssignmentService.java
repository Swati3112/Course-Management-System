package com.app.AssignmentManagement.service;

import java.util.List;

import com.app.AssignmentManagement.model.entity.Assignment;

public interface AssignmentService {

	public void deleteAssignmentByID(int id);

	public Assignment createAssignment(Assignment s);

	public Assignment getAssignmentByID(int id);

	public Assignment updateAssignmentByID(int id, Assignment assignment);

	public List<Assignment> findAllAssignment();

}
