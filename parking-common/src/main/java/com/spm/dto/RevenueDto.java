package com.spm.dto;

/**
 * Created by Vincent on 02/10/2018
 */
public class RevenueDto {
	
	private String id;
	
	private String label;
	
	private long totalCheckin;
	
	private  long totalCheckout;
	
	private long totalPrice;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
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
