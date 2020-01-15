package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cards")
public class CardsEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="code")
	private String code;
	
	@Column(name="stt")
	private  String  stt;
	
	@Column(name="vehicle_id")
	private long vehicleId;
	
	@Column(name="monthly_card_id")
	private long monthlyCardId;
	
	@Column(name="created")
	private long  created;
	
	@Column(name="updated")
	private  long updated;
	
	@Column(name="disable")
	private int disable;
	
	@Column(name="admin_id")
	private long  adminId;
	
	@Column(name="company_id")
	private long companyId;

	@Column(name="area_id")
	private Long areaId;
	
	@Column(name="project_id")
	private Long projectId;
	
	@Column(name="last_sync")
	private Long lastSync;
	
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

	public Long getLastSync() {
		return lastSync;
	}

	public void setLastSync(Long lastSync) {
		this.lastSync = lastSync;
	}
	
}
