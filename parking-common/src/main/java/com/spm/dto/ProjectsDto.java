package com.spm.dto;

public class ProjectsDto {

	private Long id;
	
	private CompanyDto company;
	
	private String name;
	
	private String address;
	
	private boolean disable;
	
	private String created;
	
	private String updated;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

}
