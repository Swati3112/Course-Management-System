package com.app.UserManagement.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.app.UserManagement.exception.ExceptionOccured;
import com.app.UserManagement.model.dto.RoleNames;
import com.app.UserManagement.model.entity.Role;
import com.app.UserManagement.model.entity.User;
import com.app.UserManagement.model.repository.RoleRepository;
import com.app.UserManagement.model.repository.UserRepository;
import com.app.UserManagement.payload.LoginRequest;
import com.app.UserManagement.payload.MessageResponse;
import com.app.UserManagement.payload.SignupRequest;
import com.app.UserManagement.security.jwt.JwtResponse;
import com.app.UserManagement.security.jwt.JwtUtils;
import com.app.UserManagement.service.UserService;
import com.app.UserManagement.service.impl.UserDetailsImpl;
import com.app.UserManagement.service.impl.UserDetailsServiceImpl;

import ch.qos.logback.classic.pattern.Util;

@RequestMapping("/user")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(
				new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
	}

	@ResponseBody
	@PutMapping("/update/{id}")
	public User update(@RequestBody SignupRequest request, @PathVariable long id) {
		
		return userService.updateUser(id, new User(request.getFirstName(), request.getLastName(), request.getEmail(),request.getPassword(), request.getUserName()), request.getRole());
	}

	@GetMapping("/{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public User getUser(@PathVariable long id) {
		return userService.findById(id);
	}

	@GetMapping("get-user-details-by-username/{name}")
	public User getUserDetailsByUserName(@PathVariable(value = "name") String name) {
		return userService.getDetailsByUserName(name);

	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		userService.saveUser(new User(signUpRequest.getFirstName(), signUpRequest.getLastName(), signUpRequest.getEmail(),
			signUpRequest.getPassword(), signUpRequest.getUserName()),signUpRequest.getRole());
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@ResponseBody
	@GetMapping("/delete/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String delete(@PathVariable Long userId) {
		userService.removeById(userId);
		return "deleted";
	}

	@ResponseBody
	@DeleteMapping("/removeAll")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Boolean removeAll() {
		return userService.removeAll();
	}
}
