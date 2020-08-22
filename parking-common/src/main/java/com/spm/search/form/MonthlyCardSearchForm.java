package com.spm.search.form;

public class MonthlyCardSearchForm {
	
	private String cardCode;
	private String vehicleId;
	private int statusDate;
	private int numberEndDate;
	private String customerName;
	private long projectId;
	
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}
	public int getStatusDate() {
		return statusDate;
	}
	public void setStatusDate(int statusDate) {
		this.statusDate = statusDate;
	}
	public int getNumberEndDate() {
		return numberEndDate;
	}
	public void setNumberEndDate(int numberEndDate) {
		this.numberEndDate = numberEndDate;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getProjectId() {
		return projectId;
	}
	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}
	
	
}
