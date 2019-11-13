package net.spm.jpa.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vincent on 02/10/2018
 */
@Entity
@Table(name="orders")
public class OrdersEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="card_id")
	private String cardId;
	
	@Column(name="card_code")
	private String cardCode;
	
	@Column(name="card_stt")
	private String cardStt;
	
	@Column(name="checkin_time")
	private Long checkinTime;
	
	@Column(name="checkout_time")
	private Long checkoutTime;
	
	@Column(name="car_number")
	private String carNumber;
	
	@Column(name="admin_checkin_id")
	private int adminCheckinId;
	
	@Column(name="admin_checkin_name")
	private String adminCheckinName;
	
	@Column(name="vehicle_code")
	private String vehicleCode;
	
	@Column(name="admin_checkout_id")
	private int adminCheckoutId;
	
	@Column(name="admin_checkout_name")
	private String adminCheckoutName;
	
	@Column(name="monthly_card_id")
	private Long  monthlyCardId;
	
	@Column(name="vehicle_id")
	private Long vehicleId;
	
	@Column(name="vehicle_name")
	private String vehicleName;
	
	@Column(name="is_card_lost")
	private int isCardLost;
	
	@Column(name="total_price")
	private Long totalPrice;
	
	@Column(name="pc_name")
	private String pcName;
	
	@Column(name="account")
	private String account;
	
	@Column(name="created")
	private Long created;
	
	@Column(name="updated")
	private Long updated;
	
	@Column(name="disable")
	private boolean dissable;
	
	@Column(name="customer_name")
	private String customerName;
	
	@Column(name="notes")
	private String notes;
	
	@Column(name="image_in_1")
	private String imageIn1;
	
	@Column(name="image_in_2")
	private String imageIn2;
	
	@Column(name="car_number_in")
	private String carNumberIn;
	
	@Column(name="image_out_1")
	private String imageOut1;
	
	@Column(name="image_out_2")
	private String imageOut2;
	
	@Column(name="car_number_out")
	private String carNumberOut;
	
	@Column(name="order_id")
	private  Long orderId;
	
	@Column(name="area_id")
	private Long areaId;
	
	@Column(name="project_id")
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

	public void setCheckinTime(Long checkinTime) {
		this.checkinTime = checkinTime;
	}

	public Long getCheckoutTime() {
		return checkoutTime;
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
		return adminCheckoutName;
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
