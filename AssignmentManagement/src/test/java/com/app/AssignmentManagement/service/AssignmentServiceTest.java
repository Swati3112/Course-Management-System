package com.app.AssignmentManagement.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.AssignmentManagement.exception.ExceptionOccured;
import com.app.AssignmentManagement.model.dto.Course;
import com.app.AssignmentManagement.model.entity.Assignment;
import com.app.AssignmentManagement.model.repository.AssignmentRepository;
import com.app.AssignmentManagement.service.impl.AssignmentServiceImpl;
import com.app.AssignmentManagement.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class AssignmentServiceTest {

	@Mock
	private AssignmentRepository repository;

	@InjectMocks
	private AssignmentServiceImpl service;
	private Assignment assignment = new Assignment("Java Assignment", new Date(), new Date(), 1, "swati3112");
	private Course course = new Course("Java Tutorial", new Date(), null, "swati3112", null);

	@Test
	public void saveAssignmentTest() {

		when(repository.save(any(Assignment.class))).thenReturn(assignment);
		Assignment result;
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when( () -> Util.getCourseDetails(course.getId())).thenReturn(course);
			service.createAssignment(assignment);
		}
		verify(repository, times(1)).save(assignment);
	}

	@Test
	public void removeByIdTest() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(assignment));
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when( () -> Util.getCourseDetails(course.getId())).thenReturn(course);
			service.deleteAssignmentByID(1);
		}
		verify(repository, times(1)).delete(any());
	}

	@Test
	public void findByIdTest() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(assignment));
		Assignment result;
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when( () -> Util.getCourseDetails(course.getId())).thenReturn(course);
			result = (Assignment) service.getAssignmentByID(1);

		}

		Assert.assertEquals(result.getTitle(), assignment.getTitle());
	}
	@Test
	public void findAllTest() {

		List<Assignment> assList= new ArrayList<>();
		assList.add(assignment);
		when(repository.findAllByUserName("swati3112")).thenReturn(assList);
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
		    List<Assignment> result = service.findAllAssignment();
		    Assert.assertEquals(result.size(), assList.size());
		}
	}

	@Test
	public void findByIdExceptionTest() {
		when(repository.findById(anyInt())).thenThrow(ExceptionOccured.class);
		Assert.assertThrows(ExceptionOccured.class, () -> service.getAssignmentByID(1));
	}

	@Test
	public void updateAssignmentByIDTest() {
		assignment.setId(1);
		when(repository.findById(anyInt())).thenReturn(Optional.of(assignment));
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when( () -> Util.getCourseDetails(course.getId())).thenReturn(course);
			service.updateAssignmentByID(1, assignment);
		}
		verify(repository, times(1)).save(assignment);
	}
}
