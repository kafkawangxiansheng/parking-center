package com.spm.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Vincent on 02/10/2018
 */
public class RevenueDto {
	
	private String id;
	
	@JsonProperty("total_checkin")
	private long totalCheckin;
	
	@JsonProperty("total_checkout")
	private  long totalCheckout;
	
	@JsonProperty("total_price")
	private long totalPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
