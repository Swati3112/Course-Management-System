package com.app.AssignmentManagement.service;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.RestTemplate;

import com.app.AssignmentManagement.model.dto.User;
import com.app.AssignmentManagement.service.impl.UserDetailsImpl;
import com.app.AssignmentManagement.service.impl.UserDetailsServiceImpl;




@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	@InjectMocks
	private UserDetailsServiceImpl detailsServiceImpl;

	@Test
	public void loadUserByUsernameTest()
	{
		User user=new User("first", "user1", "user1@mail.com", "user1", "user1");
		UserDetails userDetails = new UserDetailsImpl(1l, "user1", null, "user1", null);
		Mockito.doReturn(user).when(restTemplate).getForObject(nullable(String.class), Mockito.<Class<?>>any());
		UserDetails result;
		try (MockedStatic<UserDetailsImpl> userDetailsMock = mockStatic(UserDetailsImpl.class)) {
			userDetailsMock.when(()->UserDetailsImpl.build(any())).thenReturn(userDetails);
			result=(UserDetailsImpl) detailsServiceImpl.loadUserByUsername(user.getUserName());
		}
		
		Assert.assertEquals(result.getUsername(), user.getUserName());
		Assert.assertEquals(result.getPassword(), user.getPassword());
	}
}
