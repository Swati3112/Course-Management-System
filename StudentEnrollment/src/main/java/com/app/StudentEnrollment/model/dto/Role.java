package com.app.StudentEnrollment.model.dto;

import javax.persistence.*;


@Entity
@Table(name = "roles")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
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
