package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="price_configs")
public class PriceConfigEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_id")
	private long projectId;
	
	@Column(name="code")
	private String code;
	
    @Column(name="id_part")
	private String idPart;
    
    @Column(name="parking_type_id")
	private int parkingTypeID;
    
    @Column(name="day_cost")
	private int dayCost;
    
    @Column(name="night_cost")
	private int nightCost;
    
    @Column(name="day_night_cost")
	private int dayNightCost;
    
    @Column(name="interval_between_day_night")
	private int intervalBetweenDayNight;
    
    @Column(name="start_hour_night")
	private int startHourNight;
    
    @Column(name="end_hour_night")
	private int endHourNight;
    
    @Column(name="hour_milestone1")
	private int hourMilestone1;
    
    @Column(name="hour_milestone2")
	private int hourMilestone2;
    
    @Column(name="hour_milestone3")
	private int hourMilestone3;
    
    @Column(name="cost_milestone1")
	private int costMilestone1;
    
    @Column(name="cost_milestone2")
	private int costMilestone2;
    
    @Column(name="cost_milestone3")
	private int costMilestone3;
    
    @Column(name="cycle_milestone3")
	private int cycleMilestone3;
    
    @Column(name="is_add")
	private String isAdd;
    
    @Column(name="cycle_ticket_month")
	private int cycleTicketMonth;
    
    @Column(name="cost_ticket_month")
	private int costTicketMonth;
    
    @Column(name="min_minute")
	private int minMinute;
    
    @Column(name="min_cost")
	private int minCost;
    
    @Column(name="`limit`")
	private int limit;
    
    @Column(name="cost_milestone4")
	private int costMilestone4;
    
    @Column(name="hour_milestone4")
	private int hourMilestone4;
    
    @Column(name="hour_milestone_night1")
	private int hourMilestoneNight1;
    
    @Column(name="hour_milestone_night2")
	private int hourMilestoneNight2;
    
    @Column(name="hour_milestone_night3")
	private int hourMilestoneNight3;
    
    @Column(name="hour_milestone_night4")
	private int hourMilestoneNight4;
    
    @Column(name="cost_milestone_night1")
	private int costMilestoneNight1;
    
    @Column(name="cost_milestone_night2")
	private int costMilestoneNight2;
    
    @Column(name="cost_milestone_night3")
	private int costMilestoneNight3;
    
    @Column(name="cost_milestone_night4")
	private int costMilestoneNight4;
    
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

	public String getIdPart() {
		return idPart;
	}

	public void setIdPart(String idPart) {
		this.idPart = idPart;
	}

	public int getParkingTypeID() {
		return parkingTypeID;
	}

	public void setParkingTypeID(int parkingTypeID) {
		this.parkingTypeID = parkingTypeID;
	}

	public int getDayCost() {
		return dayCost;
	}

	public void setDayCost(int dayCost) {
		this.dayCost = dayCost;
	}

	public int getNightCost() {
		return nightCost;
	}

	public void setNightCost(int nightCost) {
		this.nightCost = nightCost;
	}

	public int getDayNightCost() {
		return dayNightCost;
	}

	public void setDayNightCost(int dayNightCost) {
		this.dayNightCost = dayNightCost;
	}

	public int getIntervalBetweenDayNight() {
		return intervalBetweenDayNight;
	}

	public void setIntervalBetweenDayNight(int intervalBetweenDayNight) {
		this.intervalBetweenDayNight = intervalBetweenDayNight;
	}

	public int getStartHourNight() {
		return startHourNight;
	}

	public void setStartHourNight(int startHourNight) {
		this.startHourNight = startHourNight;
	}

	public int getEndHourNight() {
		return endHourNight;
	}

	public void setEndHourNight(int endHourNight) {
		this.endHourNight = endHourNight;
	}

	public int getHourMilestone1() {
		return hourMilestone1;
	}

	public void setHourMilestone1(int hourMilestone1) {
		this.hourMilestone1 = hourMilestone1;
	}

	public int getHourMilestone2() {
		return hourMilestone2;
	}

	public void setHourMilestone2(int hourMilestone2) {
		this.hourMilestone2 = hourMilestone2;
	}

	public int getHourMilestone3() {
		return hourMilestone3;
	}

	public void setHourMilestone3(int hourMilestone3) {
		this.hourMilestone3 = hourMilestone3;
	}

	public int getCostMilestone1() {
		return costMilestone1;
	}

	public void setCostMilestone1(int costMilestone1) {
		this.costMilestone1 = costMilestone1;
	}

	public int getCostMilestone2() {
		return costMilestone2;
	}

	public void setCostMilestone2(int costMilestone2) {
		this.costMilestone2 = costMilestone2;
	}

	public int getCostMilestone3() {
		return costMilestone3;
	}

	public void setCostMilestone3(int costMilestone3) {
		this.costMilestone3 = costMilestone3;
	}

	public int getCycleMilestone3() {
		return cycleMilestone3;
	}

	public void setCycleMilestone3(int cycleMilestone3) {
		this.cycleMilestone3 = cycleMilestone3;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}

	public int getCycleTicketMonth() {
		return cycleTicketMonth;
	}

	public void setCycleTicketMonth(int cycleTicketMonth) {
		this.cycleTicketMonth = cycleTicketMonth;
	}

	public int getCostTicketMonth() {
		return costTicketMonth;
	}

	public void setCostTicketMonth(int costTicketMonth) {
		this.costTicketMonth = costTicketMonth;
	}

	public int getMinMinute() {
		return minMinute;
	}

	public void setMinMinute(int minMinute) {
		this.minMinute = minMinute;
	}

	public int getMinCost() {
		return minCost;
	}

	public void setMinCost(int minCost) {
		this.minCost = minCost;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCostMilestone4() {
		return costMilestone4;
	}

	public void setCostMilestone4(int costMilestone4) {
		this.costMilestone4 = costMilestone4;
	}

	public int getHourMilestone4() {
		return hourMilestone4;
	}

	public void setHourMilestone4(int hourMilestone4) {
		this.hourMilestone4 = hourMilestone4;
	}

	public int getHourMilestoneNight1() {
		return hourMilestoneNight1;
	}

	public void setHourMilestoneNight1(int hourMilestoneNight1) {
		this.hourMilestoneNight1 = hourMilestoneNight1;
	}

	public int getHourMilestoneNight2() {
		return hourMilestoneNight2;
	}

	public void setHourMilestoneNight2(int hourMilestoneNight2) {
		this.hourMilestoneNight2 = hourMilestoneNight2;
	}

	public int getHourMilestoneNight3() {
		return hourMilestoneNight3;
	}

	public void setHourMilestoneNight3(int hourMilestoneNight3) {
		this.hourMilestoneNight3 = hourMilestoneNight3;
	}

	public int getHourMilestoneNight4() {
		return hourMilestoneNight4;
	}

	public void setHourMilestoneNight4(int hourMilestoneNight4) {
		this.hourMilestoneNight4 = hourMilestoneNight4;
	}

	public int getCostMilestoneNight1() {
		return costMilestoneNight1;
	}

	public void setCostMilestoneNight1(int costMilestoneNight1) {
		this.costMilestoneNight1 = costMilestoneNight1;
	}

	public int getCostMilestoneNight2() {
		return costMilestoneNight2;
	}

	public void setCostMilestoneNight2(int costMilestoneNight2) {
		this.costMilestoneNight2 = costMilestoneNight2;
	}

	public int getCostMilestoneNight3() {
		return costMilestoneNight3;
	}

	public void setCostMilestoneNight3(int costMilestoneNight3) {
		this.costMilestoneNight3 = costMilestoneNight3;
	}

	public int getCostMilestoneNight4() {
		return costMilestoneNight4;
	}

	public void setCostMilestoneNight4(int costMilestoneNight4) {
		this.costMilestoneNight4 = costMilestoneNight4;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	
    
}
