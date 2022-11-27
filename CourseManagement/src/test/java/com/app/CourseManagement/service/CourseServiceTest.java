package com.app.CourseManagement.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

import java.util.Date;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import com.app.CourseManagement.exception.ExceptionOccured;
import com.app.CourseManagement.model.entity.Course;
import com.app.CourseManagement.model.repository.CourseRepository;
import com.app.CourseManagement.service.impl.CourseServiceImpl;
import com.app.CourseManagement.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class CourseServiceTest {

	@Mock
	private CourseRepository repository;

	@InjectMocks
	private CourseServiceImpl service;
	private Course course = new Course("Java Tutorial", new Date(), null, "swati3112", null);

	@Test
	public void saveCourseTest() {

		when(repository.save(any(Course.class))).thenReturn(course);
		Course result;
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			service.createCourse(course);
		}
		verify(repository, times(1)).save(course);
	}

	@Test
	public void removeByIdTest() {
		when(repository.findById(anyInt())).thenReturn(Optional.of(course));
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			service.deleteCourseByID(1);
		}
		verify(repository, times(1)).delete(any());
	}

	@Test
	public void findByIdTest() {

		when(repository.findById(anyInt())).thenReturn(Optional.of(course));
		Course result;
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			result = (Course) service.getCourseByID(1);

		}

		Assert.assertEquals(result.getTitle(), course.getTitle());
	}

	@Test
	public void findByIdExceptionTest() {
		when(repository.findById(anyInt())).thenThrow(ExceptionOccured.class);
		Assert.assertThrows(ExceptionOccured.class, () -> service.getCourseByID(1));
	}
	
	@Test
	public void updateCourseByIDTest() {
		course.setId(1);
		when(repository.findById(anyInt())).thenReturn(Optional.of(course));
		try (MockedStatic<Util> util = mockStatic(Util.class)) {
			util.when(Util::getUserName).thenReturn("swati3112");
			service.updateCourseByID(1, course);
		}
		verify(repository, times(1)).save(course);
	}

}
