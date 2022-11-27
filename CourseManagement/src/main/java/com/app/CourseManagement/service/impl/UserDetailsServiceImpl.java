package com.app.CourseManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.app.CourseManagement.model.dto.User;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		String uri="http://localhost:8080/user/get-user-details-by-username/"+username;
		User u=restTemplate.getForObject(uri, User.class);
		System.out.println(u.toString());
		return UserDetailsImpl.build(u);
	}

}