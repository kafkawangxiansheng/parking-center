package com.spm.dto;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 02/10/2018
 */
public class OrderDto {
	
	
	private Long id;
	
	private String cardId;
	
	private String cardCode;
	
	private String cardStt;
	
	private Long checkinTime;
	
	private Long checkoutTime;
	
	private String carNumber;
	
	private int adminCheckinId;
	
	private String adminCheckinName;
	
	private String vehicleCode;
	
	private int adminCheckoutId;
	
	private String adminCheckoutName;
	
	private Long  monthlyCardId;
	
	private Long vehicleId;
	
	private String vehicleName;
	
	private int isCardLost;
	
	private Long totalPrice;
	
	private String pcName;
	
	private String account;
	
	private Long created;
	
	private Long updated;
	
	private boolean dissable;
	
	private String customerName;
	
	private String notes;
	
	private String imageIn1;
	
	private String imageIn2;
	
	private String carNumberIn;
	
	private String imageOut1;
	
	private String imageOut2;
	
	private String carNumberOut;
	
	private  Long orderId;
	
	private Long areaId;
	
	private Long projectId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

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

	public Long getCheckinTime() {
		return checkinTime;
	}
	
	public String getCheckinTimeInFormat() {
		if(checkinTime == null || checkinTime == 0) {
			return "Chưa vào";
		}
		Date date = new Date(checkinTime);
	    Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    return format.format(date);
	}

	public void setCheckinTime(Long checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Long getCheckoutTime() {
		return checkoutTime;
	}
	
	public String getCheckoutTimeInFormat() {
		if(checkoutTime == null || checkoutTime == 0) {
			return "Chưa ra";
		}
		Date date = new Date(checkoutTime);
	    Format format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	    return format.format(date);
	}
	
	public void setCheckoutTime(Long checkoutTime) {
		this.checkoutTime = checkoutTime;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public int getAdminCheckinId() {
		return adminCheckinId;
	}

	public void setAdminCheckinId(int adminCheckinId) {
		this.adminCheckinId = adminCheckinId;
	}

	public String getAdminCheckinName() {
		return adminCheckinName;
	}

	public void setAdminCheckinName(String adminCheckinName) {
		this.adminCheckinName = adminCheckinName;
	}

	public String getVehicleCode() {
		return vehicleCode;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public int getAdminCheckoutId() {
		return adminCheckoutId;
	}

	public void setAdminCheckoutId(int adminCheckoutId) {
		this.adminCheckoutId = adminCheckoutId;
	}

	public String getAdminCheckoutName() {
		return adminCheckoutName != null ?  adminCheckoutName:"-";
	}

	public void setAdminCheckoutName(String adminCheckoutName) {
		this.adminCheckoutName = adminCheckoutName;
	}

	public Long getMonthlyCardId() {
		return monthlyCardId;
	}

	public void setMonthlyCardId(Long monthlyCardId) {
		this.monthlyCardId = monthlyCardId;
	}

	public Long getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(Long vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleName() {
		return vehicleName;
	}

	public void setVehicleName(String vehicleName) {
		this.vehicleName = vehicleName;
	}

	public int getIsCardLost() {
		return isCardLost;
	}

	public void setIsCardLost(int isCardLost) {
		this.isCardLost = isCardLost;
	}

	public Long getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Long totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getPcName() {
		return pcName;
	}

	public void setPcName(String pcName) {
		this.pcName = pcName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Long getCreated() {
		return created;
	}

	public void setCreated(Long created) {
		this.created = created;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public boolean isDissable() {
		return dissable;
	}

	public void setDissable(boolean dissable) {
		this.dissable = dissable;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getImageIn1() {
		return imageIn1;
	}

	public void setImageIn1(String imageIn1) {
		this.imageIn1 = imageIn1;
	}

	public String getImageIn2() {
		return imageIn2;
	}

	public void setImageIn2(String imageIn2) {
		this.imageIn2 = imageIn2;
	}

	public String getCarNumberIn() {
		return carNumberIn;
	}

	public void setCarNumberIn(String carNumberIn) {
		this.carNumberIn = carNumberIn;
	}

	public String getImageOut1() {
		return imageOut1;
	}

	public void setImageOut1(String imageOut1) {
		this.imageOut1 = imageOut1;
	}

	public String getImageOut2() {
		return imageOut2;
	}

	public void setImageOut2(String imageOut2) {
		this.imageOut2 = imageOut2;
	}

	public String getCarNumberOut() {
		return carNumberOut;
	}

	public void setCarNumberOut(String carNumberOut) {
		this.carNumberOut = carNumberOut;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
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
	
}
