package com.spm.dto;

public class VehicleDto {

	private Long id;
	
	private String name;
	
	private String code;
	
	private long monthlyCost;
	
	private long type;
	
	private long cardType;
	
	private ProjectsDto project;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(long monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	public long getType() {
		return type;
	}

	public void setType(long type) {
		this.type = type;
	}

	public long getCardType() {
		return cardType;
	}

	public void setCardType(long cardType) {
		this.cardType = cardType;
	}

	public ProjectsDto getProject() {
		return project;
	}

	public void setProject(ProjectsDto project) {
		this.project = project;
	}
	
}
