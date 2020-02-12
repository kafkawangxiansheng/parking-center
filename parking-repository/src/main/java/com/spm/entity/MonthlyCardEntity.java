package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="monthly_cards")
public class MonthlyCardEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="card_code")
	private String cardCode;
	
	@Column(name="vehicle_id")
	private Long vehicleId;
	
	@Column(name="car_number")
	private String carNumber;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="id_number")
	private String idNumber;
	
	@Column(name="email")
	private String email;
	
	@Column(name="company")
	private String company;
	
	@Column(name="address")
	private String  address;
	
	@Column(name="brand")
	private  String brand;
	
	@Column(name="parking_fee")
	private long parkingFee;
	
	@Column(name="start_date")
	private long startDate;
	
	@Column(name="end_date")
	private long endDate;
	
	@Column(name="created")
	private long created;
	
	@Column(name="updated")
	private long updated;
	
	@Column(name="admin_id")
	private long adminId;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private CompanyEntity companyDto;
	
	@Column(name="area_id")
	private Long areaId;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private ProjectsEntity project;

	@Column(name="last_sync")
	private Long lastSync;

	@Column(name="disabled")
	private boolean disabled;

	@Column(name="deleted")
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

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	public CompanyEntity getCompanyDto() {
		return companyDto;
	}

	public void setCompanyDto(CompanyEntity companyDto) {
		this.companyDto = companyDto;
	}

	public Long getAreaId() {
		return areaId;
	}

	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	public ProjectsEntity getProject() {
		return project;
	}

	public void setProject(ProjectsEntity project) {
		this.project = project;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}
	public Long getLastSync() {
		return lastSync;
	}

	public void setLastSync(Long lastSync) {
		this.lastSync = lastSync;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
}
