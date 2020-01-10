package com.spm.dtos;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardsDto {

	private Long id;

	private String code;

	private String stt;

	@JsonProperty("vehicle_id")
	private long vehicleId;

	@JsonProperty("monthly_card_id")
	private long monthlyCardId;

	@JsonProperty("created")
	private Date created;

	@JsonProperty("updated")
	private Date updated;

	@JsonProperty("disable")
	private int disable;

	@JsonProperty("admin_id")
	private long adminId;

	@JsonProperty("company_id")
	private long companyId;
	
	@JsonProperty("area_id")
	private Long areaId;
	
	@JsonProperty("project_id")
	private Long projectId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getStt() {
		return stt;
	}

	public void setStt(String stt) {
		this.stt = stt;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getMonthlyCardId() {
		return monthlyCardId;
	}

	public void setMonthlyCardId(long monthlyCardId) {
		this.monthlyCardId = monthlyCardId;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public int getDisable() {
		return disable;
	}

	public void setDisable(int disable) {
		this.disable = disable;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

}
