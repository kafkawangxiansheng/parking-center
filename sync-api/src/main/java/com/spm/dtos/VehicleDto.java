package com.spm.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class VehicleDto {
	
	private Long id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("code")
	private String code;
	
	@JsonProperty("monthly_cost")
	private long monthlyCost;
	
	@JsonProperty("vehicel_type")
	private int vehicleType;
	
	@JsonProperty("vehicel_id")
	private int vehicleId;
	
	@JsonProperty("card_type")
	private int cardType;
	
	@JsonProperty("project_id")
	private Long projectId;
	
	@JsonProperty("updated")
	private Long updated;
	
	@JsonProperty("disabled")
	private int disabled;

	@JsonProperty("deleted")
	private int deleted;
	
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

	

	public int getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(int vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}
	
}
