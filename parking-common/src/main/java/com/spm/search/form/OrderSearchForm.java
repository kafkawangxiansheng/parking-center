package com.spm.search.form;

/**
 * Created by Vincent on 02/10/2018
 */
public class OrderSearchForm {
	
	
	private String cardCode;
	
	private String cardStt;
	
	private String dateFrom;
	
	private String dateTo;
	
	private String carNumber;
	
	private String projectId;
	
	private int isMonthlyCard;

	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getCardStt() {
		return cardStt;
	}

	public void setCardStt(String cardStt) {
		this.cardStt = cardStt;
	}

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	
	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getIsMonthlyCard() {
		return isMonthlyCard;
	}

	public void setIsMonthlyCard(int isMonthlyCard) {
		this.isMonthlyCard = isMonthlyCard;
	}
	
}
