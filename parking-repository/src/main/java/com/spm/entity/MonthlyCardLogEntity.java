package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="monthly_ticket_logs")
public class MonthlyCardLogEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_id")
	private Long projectID;
	
	@Column(name="log_type_id")
	private int logTypeID;
	
	@Column(name="process_date")
	private Long processDate;
	
	@Column(name="ticket_month_id")
	private String ticketMonthID;
	
	@Column(name="ticket_month_identify")
	private String ticketMonthIdentify;
	
	@Column(name="digit")
	private String digit;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="cmnd")
	private String cmnd;
	
	@Column(name="company")
	private String company;
	
	@Column(name="email")
	private String email;
	
	@Column(name="address")
	private String address;
	
	@Column(name="car_kind")
	private String carKind;
	
	@Column(name="id_part")
	private String idPart;
	
	@Column(name="registration_date")
	private Long registrationDate;
	
	@Column(name="expiration_date")
	private Long expirationDate;
	
	@Column(name="note")
	private String note;
	
	@Column(name="charges_amount")
	private String chargesAmount;
	
	@Column(name="status")
	private int status;
	
	@Column(name="account")
	private String account;
	
	@Column(name="images")
	private String images;
	
	@Column(name="day_unlimit")
	private Long dayUnlimit;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getProjectID() {
		return projectID;
	}

	public void setProjectID(Long projectID) {
		this.projectID = projectID;
	}

	public int getLogTypeID() {
		return logTypeID;
	}

	public void setLogTypeID(int logTypeID) {
		this.logTypeID = logTypeID;
	}

	public Long getProcessDate() {
		return processDate;
	}

	public void setProcessDate(Long processDate) {
		this.processDate = processDate;
	}

	public String getTicketMonthID() {
		return ticketMonthID;
	}

	public void setTicketMonthID(String ticketMonthID) {
		this.ticketMonthID = ticketMonthID;
	}

	public String getTicketMonthIdentify() {
		return ticketMonthIdentify;
	}

	public void setTicketMonthIdentify(String ticketMonthIdentify) {
		this.ticketMonthIdentify = ticketMonthIdentify;
	}

	public String getDigit() {
		return digit;
	}

	public void setDigit(String digit) {
		this.digit = digit;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCarKind() {
		return carKind;
	}

	public void setCarKind(String carKind) {
		this.carKind = carKind;
	}

	public String getIdPart() {
		return idPart;
	}

	public void setIdPart(String idPart) {
		this.idPart = idPart;
	}

	public Long getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Long registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Long getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Long expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getChargesAmount() {
		return chargesAmount;
	}

	public void setChargesAmount(String chargesAmount) {
		this.chargesAmount = chargesAmount;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public Long getDayUnlimit() {
		return dayUnlimit;
	}

	public void setDayUnlimit(Long dayUnlimit) {
		this.dayUnlimit = dayUnlimit;
	}
	
	
}
