package com.app.UserManagement.payload;

import java.util.List;
import javax.validation.constraints.*;

public class SignupRequest {
	@NotBlank
    private String firstName;

    private String lastName;

    @Email
    private String email;
    @NotBlank
    private String password;

    @NotBlank
    private String userName;

    @NotNull
	private List<String> role;
	
	

	public SignupRequest(String firstName, String lastName, String email, String password,
			String userName, List<String> role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	
		this.userName = userName;
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public List<String> getRole() {
		return role;
	}

	public void setRole(List<String> role) {
		this.role = role;
	}

  
}
