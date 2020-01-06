package com.spm.search.form;

/**
 * Created by Vincent on 02/10/2018
 */
public class RevenueSearchForm {
	
	
	private int projectId;
	
	private String dateFrom;
	private String hourFrom;
	private String minFrom;
	
	private String dateTo;
	private String hourTo;
	private String minTo;
	
	private String employeeId;

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getHourFrom() {
		return hourFrom;
	}

	public void setHourFrom(String hourFrom) {
		this.hourFrom = hourFrom;
	}

	public String getMinFrom() {
		return minFrom;
	}

	public void setMinFrom(String minFrom) {
		this.minFrom = minFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getHourTo() {
		return hourTo;
	}

	public void setHourTo(String hourTo) {
		this.hourTo = hourTo;
	}

	public String getMinTo() {
		return minTo;
	}

	public void setMinTo(String minTo) {
		this.minTo = minTo;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	
	
}
