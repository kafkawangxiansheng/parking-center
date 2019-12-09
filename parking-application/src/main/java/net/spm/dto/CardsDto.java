package net.spm.dto;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CardsDto {

	private Long id;

	@JsonProperty("code")
	private String code;

	@JsonProperty("stt")
	private long stt;

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

	public long getStt() {
		return stt;
	}

	public void setStt(long stt) {
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

}
