package com.app.UserManagement.service;

import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.app.UserManagement.model.entity.User;
import com.app.UserManagement.model.repository.UserRepository;
import com.app.UserManagement.service.impl.UserDetailsImpl;
import com.app.UserManagement.service.impl.UserDetailsServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsServiceTest {
	
	@Mock
	private UserRepository userRepository;
	
	@InjectMocks
	private UserDetailsServiceImpl detailsServiceImpl;

	@Test
	public void loadUserByUsernameTest()
	{
		User user=new User("first", "user1", "user1@mail.com", "user1", "user1");
		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		UserDetailsImpl result=(UserDetailsImpl) detailsServiceImpl.loadUserByUsername(user.getUserName());
		Assert.assertEquals(result.getUsername(), user.getUserName());
		Assert.assertEquals(result.getPassword(), user.getPassword());
	}
}
