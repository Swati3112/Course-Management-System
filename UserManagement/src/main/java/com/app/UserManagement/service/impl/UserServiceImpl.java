package com.app.UserManagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.UserManagement.exception.ExceptionOccured;
import com.app.UserManagement.model.dto.RoleNames;
import com.app.UserManagement.model.entity.*;
import com.app.UserManagement.model.repository.RoleRepository;
import com.app.UserManagement.model.repository.UserRepository;
import com.app.UserManagement.payload.MessageResponse;
import com.app.UserManagement.service.UserService;
import com.app.UserManagement.util.Util;

import java.util.*;


@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;


    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void saveUser(User user, List<String> strRoles) {
        if (user.getId() == null) {
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            
        }
        if (userRepository.existsByUserName(user.getUserName()))throw new ExceptionOccured("Error: Username is already taken!", HttpStatus.BAD_REQUEST);

		if (userRepository.existsByEmail(user.getEmail())) throw new ExceptionOccured("Error: Email is already in use!", HttpStatus.BAD_REQUEST);


		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleNames.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(RoleNames.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "INSTRUCTOR":
					Role modRole = roleRepository.findByName(RoleNames.ROLE_INSTRUCTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleNames.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
    }


    @Override
    public boolean removeAll() {
        userRepository.deleteAll();
        return Boolean.TRUE;
    }



    @Override
    public void removeById(Long id) {
    	userRepository.findById(id).orElseThrow(()-> new ExceptionOccured("User doesn't exist with this id", HttpStatus.NOT_FOUND));
        userRepository.deleteById(id);
    }



    @Override
    public User findById(Long id) {
    	User u=userRepository.findById(id).orElseThrow(()->new ExceptionOccured("No data found with this id", HttpStatus.NOT_FOUND));
        return userRepository.findById(id).orElseThrow(()->new ExceptionOccured("No data found with this id", HttpStatus.NOT_FOUND));
    }
    
    @Override

    public User getDetailsByUserName(String name) {
    	User user = userRepository.findByUserName(name)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + name));
    	return user;
    }
    @Override
    public User updateUser(long id, User user, List<String> strRoles) {

		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(RoleNames.ROLE_STUDENT)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "ADMIN":
					Role adminRole = roleRepository.findByName(RoleNames.ROLE_ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "INSTRUCTOR":
					Role modRole = roleRepository.findByName(RoleNames.ROLE_INSTRUCTOR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(RoleNames.ROLE_STUDENT)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
    	User user2 = userRepository.findByUserName(Util.getUserName()).orElse(null);
    	User dbUser = userRepository.findById(id).orElseThrow(()-> new ExceptionOccured("No user found with this id", HttpStatus.NOT_FOUND));
		if (!strRoles.contains("ROLE_ADMIN")) {
			if (!Util.getUserName().equals(dbUser.getUserName()))
				throw new ExceptionOccured("You can not update this user details you need admin permission",
						HttpStatus.UNAUTHORIZED);
		}
		dbUser.setFirstName(user.getFirstName());
		dbUser.setLastName(user.getLastName());
		dbUser.setEmail(user.getEmail());
		dbUser.setRoles(user.getRoles());
		dbUser.setUserName(user.getUserName());
		return userRepository.save(dbUser);
    }





}
