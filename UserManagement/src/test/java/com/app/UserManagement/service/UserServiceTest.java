package com.app.UserManagement.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.UserManagement.exception.ExceptionOccured;
import com.app.UserManagement.model.dto.RoleNames;
import com.app.UserManagement.model.entity.Role;
import com.app.UserManagement.model.entity.User;
import com.app.UserManagement.model.repository.RoleRepository;
import com.app.UserManagement.model.repository.UserRepository;
import com.app.UserManagement.service.impl.UserServiceImpl;
import com.app.UserManagement.util.Util;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;
	@Mock
	private PasswordEncoder bCryptPasswordEncoder;
	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserServiceImpl userService;

	private User user = new User("first", "user1", "user1@mail.com", "user1", "user1");

	@Test
	public void saveUserTest() {

		Role role= new Role();
		role.setId(1);
		role.setName(RoleNames.ROLE_ADMIN);
		when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
		when(userRepository.save(any(User.class))).thenReturn(user);
		userService.saveUser(user, Arrays.asList("ADMIN"));
		verify(userRepository, times(1)).save(user);
	}
//	@Test
//	public void updateUserTest() {
//
//		Role role= new Role();
//		role.setId(1);
//		role.setName(RoleNames.ROLE_ADMIN);
//		when(roleRepository.findByName(any())).thenReturn(Optional.of(role));
//		when(userRepository.save(any(User.class))).thenReturn(user);
//		try (MockedStatic<Util> util = mockStatic(Util.class)) {
//			util.when(Util::getUserName).thenReturn("user1");
//			util.when(Util::getToken).thenReturn("");
//			userService.updateUser(1l, user, Arrays.asList("ADMIN"));
//			verify(userRepository, times(1)).save(user);
//		}
//		
//		
//	}

	@Test
	public void getUserByNameTest() {

		when(userRepository.findByUserName(anyString())).thenReturn(Optional.of(user));
		User result = (User) userService.getDetailsByUserName(user.getUserName());
		Assert.assertEquals(result.getUserName(), user.getUserName());

	}

	@Test
	public void getUserByNameExceptionTest() {
		when(userRepository.findByUserName(anyString())).thenThrow(ExceptionOccured.class);
		Assert.assertThrows(ExceptionOccured.class, () -> userService.getDetailsByUserName("love"));
	}

	@Test
	public void removeByIdTest() {
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		userService.removeById(1l);
		verify(userRepository, times(1)).deleteById(anyLong());
	}

	@Test
	public void removeAllTest() {
		userService.removeAll();
		verify(userRepository, times(1)).deleteAll();
	}

	@Test
	public void findByIdTest() {
		User user = new User("first", "user1", "user1@mail.com", "user1", "user1");
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		User result = (User) userService.findById(1l);
		Assert.assertEquals(result.getUserName(), user.getUserName());
	}

	@Test
	public void findByIdExceptionTest() {
		when(userRepository.findById(anyLong())).thenThrow(ExceptionOccured.class);
		Assert.assertThrows(ExceptionOccured.class, () -> userService.findById(1l));
	}

}
