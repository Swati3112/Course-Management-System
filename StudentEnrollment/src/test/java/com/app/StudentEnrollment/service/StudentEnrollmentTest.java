package com.app.StudentEnrollment.service;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import com.app.StudentEnrollment.exception.ExceptionOccured;
import com.app.StudentEnrollment.model.dto.Assignment;
import com.app.StudentEnrollment.model.dto.Course;
import com.app.StudentEnrollment.model.entity.EnrollmentName;
import com.app.StudentEnrollment.model.entity.Student;
import com.app.StudentEnrollment.model.repository.StudentRepo;
import com.app.StudentEnrollment.payload.Request;
import com.app.StudentEnrollment.service.impl.StudentEnrollmentServiceImpl;
import com.app.StudentEnrollment.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class StudentEnrollmentTest {

	@Mock
	private StudentRepo repository;

	@InjectMocks
	private StudentEnrollmentServiceImpl service;
	
	private Assignment assignment = new Assignment("Java Assignment", new Date(), new Date(), 1, "swati3112");
	private Course course = new Course("Java Tutorial", new Date(), null, "swati3112", null);
	private Request req= new Request(Arrays.asList(1)  , "sakshi6");
	

	@Test
	public void enrollForCourseTest() {

		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when( () -> Util.getCourseDetails(anyInt())).thenReturn(course);
			util.when(Util::getToken).thenReturn("");
			service.enrollForCourse(req);
		}
		verify(repository, times(1)).save(nullable(Student.class));
	}
	
	@Test
	public void enrollForAssignmentTest() {
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			assignment.setId(1);
			util.when( () -> Util.getAssignmentDetails(anyInt())).thenReturn(assignment);
			util.when(Util::getToken).thenReturn("");
			service.enrollForAssignment(req);
			
		}
		
		verify(repository, times(1)).save(nullable(Student.class));
	}


	@Test
	public void findByIdTest() {
		
		Student student=new Student("sakshi6", "swati3112", new HashSet<>());
		Student result;
		when(repository.findById(anyInt())).thenReturn(Optional.of(student));
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			course.setId(1);
			util.when(Util::getToken).thenReturn("");
			result = (Student) service.getEnrolledStudentDetails(anyInt());
		}
		Assert.assertEquals(result.getUserName(), req.getStudentUserName());
	}

	@Test
	public void findByIdExceptionTest() {
		when(repository.findById(anyInt())).thenThrow(ExceptionOccured.class);
		Assert.assertThrows(ExceptionOccured.class, () -> service.getEnrolledStudentDetails(1));
	}

	
}
