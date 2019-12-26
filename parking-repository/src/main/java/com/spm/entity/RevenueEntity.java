package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vincent on 02/10/2018
 */
@Entity
@Table(name="moto_monthly_revenue")
public class RevenueEntity {
	
	@Id
    @Column(name="id")
	private String id;
	
	@Column(name="vehicle_id")
	private int vehicleId;
	
	@Column(name="total_checkin")
	private long totalCheckin;
	
	@Column(name="total_checkout")
	private  long totalCheckout;
	
	@Column(name="total_price")
	private long totalPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public long getTotalCheckin() {
		return totalCheckin;
	}

	public void setTotalCheckin(long totalCheckin) {
		this.totalCheckin = totalCheckin;
	}

	public long getTotalCheckout() {
		return totalCheckout;
	}

	public void setTotalCheckout(long totalCheckout) {
		this.totalCheckout = totalCheckout;
	}

	public long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	
	
}
