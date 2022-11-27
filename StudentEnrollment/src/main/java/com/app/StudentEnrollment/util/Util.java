package com.app.StudentEnrollment.util;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.app.StudentEnrollment.exception.ExceptionOccured;
import com.app.StudentEnrollment.model.dto.Assignment;
import com.app.StudentEnrollment.model.dto.Course;

@Component
public class Util {
	
	public static String getUserName() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		return username;
	}

	public static String getToken() {
	    String token = null;
	    HttpServletRequest request= ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	    return request.getHeader("Authorization");
	  }
	public static Course getCourseDetails(int id) {
		String uri = "http://localhost:8081/course/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getToken());
		//System.out.println("token"+ getToken());
		RestTemplate t = new RestTemplate();
		Course c1;
		try {
			//c1 = t.getForObject(uri, Course.class);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Course> respEntity = t.exchange(uri, HttpMethod.GET, entity,Course.class);
			c1= respEntity.getBody();
			if (c1 == null)
				throw new ExceptionOccured("You can not create assignment for this course please create course first", HttpStatus.EXPECTATION_FAILED);

		} catch (Exception e) {
			throw new ExceptionOccured(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return c1;
	}
	public static Assignment getAssignmentDetails(int id) {
		String uri = "http://localhost:8083/assignment/" + id;
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", getToken());
		//System.out.println("token"+ getToken());
		RestTemplate t = new RestTemplate();
		Assignment c1;
		try {
			//c1 = t.getForObject(uri, Course.class);
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<Assignment> respEntity = t.exchange(uri, HttpMethod.GET, entity,Assignment.class);
			c1= respEntity.getBody();
			if (c1 == null)
				throw new ExceptionOccured("You can not create assignment for this course please create course first", HttpStatus.EXPECTATION_FAILED);

		} catch (Exception e) {
			throw new ExceptionOccured(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return c1;
	}
	
	
}
