package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="configs")
public class ConfigEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_id")
	private long projectId;
	
	@Column(name="code")
	private String code;
	
	@Column(name="kind")
	private String kind;
	
	@Column(name="title")
	private String title;
	
	@Column(name="is_em_save_lost_ticket")
	private int isEmSaveLostTicket;
	
	@Column(name="is_see_report")
	private int isSeeReport;
	
	@Column(name="number_of_day")
	private int numberOfDay;
	
	@Column(name="is_get_money_ticket")
	private int isGetMoneyTicket;
	
	@Column(name="lost_card")
	private int lostCard;
	
	@Column(name="bike_space")
	private int bikeSpace;
	
	@Column(name="car_space")
	private int carSpace;
	
	@Column(name="ticket_limit_day")
	private int ticketLimitDay;
	
	@Column(name="logo")
	private String logo;
	
	@Column(name="night_limit")
	private int nightLimit;
	
	@Column(name="title_report")
	private String titleReport;
	
	@Column(name="parking_type_id")
	private int parkingTypeId;
	
	@Column(name="camera1")
	private String camera1;
	
	@Column(name="camera2")
	private String camera2;
	
	@Column(name="camera3")
	private String camera3;
	
	@Column(name="camera4")
	private String camera4;
	
	@Column(name="rfid1")
	private String rfid1;
	
	@Column(name="rfid2")
	private String rfid2;
	
	@Column(name="in_out_type")
	private int inOutType;
	
	@Column(name="expired_ticket_month_type_id")
	private int expiredTicketMonthTypeId;
	
	@Column(name="parking_name")
	private String parkingName;
	
	@Column(name="calculation_ticket_month")
	private int calculationTicketMonth;
	
	@Column(name="lock_card_date")
	private int lockCardDate;
	
	@Column(name="is_auto_lock_card")
	private int isAutoLockCard;
	
	@Column(name="last_sync")
	private long lastSync;
	
	@Column(name="updated_at")
	private long updatedAt;
	
	@Column(name="deleted")
	private boolean deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getIsEmSaveLostTicket() {
		return isEmSaveLostTicket;
	}

	public void setIsEmSaveLostTicket(int isEmSaveLostTicket) {
		this.isEmSaveLostTicket = isEmSaveLostTicket;
	}

	public int getIsSeeReport() {
		return isSeeReport;
	}

	public void setIsSeeReport(int isSeeReport) {
		this.isSeeReport = isSeeReport;
	}

	public int getNumberOfDay() {
		return numberOfDay;
	}

	public void setNumberOfDay(int numberOfDay) {
		this.numberOfDay = numberOfDay;
	}

	public int getIsGetMoneyTicket() {
		return isGetMoneyTicket;
	}

	public void setIsGetMoneyTicket(int isGetMoneyTicket) {
		this.isGetMoneyTicket = isGetMoneyTicket;
	}

	public int getLostCard() {
		return lostCard;
	}

	public void setLostCard(int lostCard) {
		this.lostCard = lostCard;
	}

	public int getBikeSpace() {
		return bikeSpace;
	}

	public void setBikeSpace(int bikeSpace) {
		this.bikeSpace = bikeSpace;
	}

	public int getCarSpace() {
		return carSpace;
	}

	public void setCarSpace(int carSpace) {
		this.carSpace = carSpace;
	}

	public int getTicketLimitDay() {
		return ticketLimitDay;
	}

	public void setTicketLimitDay(int ticketLimitDay) {
		this.ticketLimitDay = ticketLimitDay;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getNightLimit() {
		return nightLimit;
	}

	public void setNightLimit(int nightLimit) {
		this.nightLimit = nightLimit;
	}

	public String getTitleReport() {
		return titleReport;
	}

	public void setTitleReport(String titleReport) {
		this.titleReport = titleReport;
	}

	public int getParkingTypeId() {
		return parkingTypeId;
	}

	public void setParkingTypeId(int parkingTypeId) {
		this.parkingTypeId = parkingTypeId;
	}

	public String getCamera1() {
		return camera1;
	}

	public void setCamera1(String camera1) {
		this.camera1 = camera1;
	}

	public String getCamera2() {
		return camera2;
	}

	public void setCamera2(String camera2) {
		this.camera2 = camera2;
	}

	public String getCamera3() {
		return camera3;
	}

	public void setCamera3(String camera3) {
		this.camera3 = camera3;
	}

	public String getCamera4() {
		return camera4;
	}

	public void setCamera4(String camera4) {
		this.camera4 = camera4;
	}

	public String getRfid1() {
		return rfid1;
	}

	public void setRfid1(String rfid1) {
		this.rfid1 = rfid1;
	}

	public String getRfid2() {
		return rfid2;
	}

	public void setRfid2(String rfid2) {
		this.rfid2 = rfid2;
	}

	public int getInOutType() {
		return inOutType;
	}

	public void setInOutType(int inOutType) {
		this.inOutType = inOutType;
	}

	public int getExpiredTicketMonthTypeId() {
		return expiredTicketMonthTypeId;
	}

	public void setExpiredTicketMonthTypeId(int expiredTicketMonthTypeId) {
		this.expiredTicketMonthTypeId = expiredTicketMonthTypeId;
	}

	public String getParkingName() {
		return parkingName;
	}

	public void setParkingName(String parkingName) {
		this.parkingName = parkingName;
	}

	public int getCalculationTicketMonth() {
		return calculationTicketMonth;
	}

	public void setCalculationTicketMonth(int calculationTicketMonth) {
		this.calculationTicketMonth = calculationTicketMonth;
	}

	public int getLockCardDate() {
		return lockCardDate;
	}

	public void setLockCardDate(int lockCardDate) {
		this.lockCardDate = lockCardDate;
	}

	public int getIsAutoLockCard() {
		return isAutoLockCard;
	}

	public void setIsAutoLockCard(int isAutoLockCard) {
		this.isAutoLockCard = isAutoLockCard;
	}

	public long getLastSync() {
		return lastSync;
	}

	public void setLastSync(long lastSync) {
		this.lastSync = lastSync;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
	
	
	
    
}
