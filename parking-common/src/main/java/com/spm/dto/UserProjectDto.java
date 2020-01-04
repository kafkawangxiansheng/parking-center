package com.spm.dto;

import java.util.Date;

public class UserProjectDto {
	
	private Long id;
	
	private UserDto user;
	
	private CompanyDto company;
	
	private ProjectsDto project;
	
	private Date createdAt;
	
	private  Date  updatedAt;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	public ProjectsDto getProject() {
		return project;
	}

	public void setProject(ProjectsDto project) {
		this.project = project;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
