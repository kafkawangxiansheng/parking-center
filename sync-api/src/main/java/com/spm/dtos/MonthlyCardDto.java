package com.spm.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MonthlyCardDto {
	
	@JsonProperty("id")
	private Long id;
	
	@JsonProperty("card_code")
	private String cardCode;
	
	@JsonProperty("car_number")
	private String carNumber;
	
	@JsonProperty("customer_name")
	private String customerName;
	
	@JsonProperty("id_number")
	private String idNumber;
	
	@JsonProperty("email")
	private String email;
	
	@JsonProperty("company")
	private String company;
	
	@JsonProperty("address")
	private String  address;
	
	@JsonProperty("brand")
	private  String brand;
	
	@JsonProperty("parking_fee")
	private long parkingFee;
	
	@JsonProperty("vehicle_id")
	private long vehicleId;
	
	@JsonProperty("start_date")
	private long startDate;
	
	@JsonProperty("end_date")
	private long endDate;
	
	@JsonProperty("created")
	private long created;
	
	@JsonProperty("updated")
	private long updated;
	
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
	
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public long getParkingFee() {
		return parkingFee;
	}

	public void setParkingFee(long parkingFee) {
		this.parkingFee = parkingFee;
	}

	public long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getStartDate() {
		return startDate;
	}

	public void setStartDate(long startDate) {
		this.startDate = startDate;
	}

	public long getEndDate() {
		return endDate;
	}

	public void setEndDate(long endDate) {
		this.endDate = endDate;
	}

	public long getCreated() {
		return created;
	}

	public void setCreated(long created) {
		this.created = created;
	}

	public long getUpdated() {
		return updated;
	}

	public void setUpdated(long updated) {
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

	public boolean getDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
}
