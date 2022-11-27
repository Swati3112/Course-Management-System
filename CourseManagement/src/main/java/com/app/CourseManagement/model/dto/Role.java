package com.app.CourseManagement.model.dto;

import javax.persistence.*;


public class Role {
	
	private Integer id;

	private RoleNames name;

	public Role() {

	}

	public Role(RoleNames name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleNames getName() {
		return name;
	}

	public void setName(RoleNames name) {
		this.name = name;
	}
}
