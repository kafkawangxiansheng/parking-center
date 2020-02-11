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
@Table(name="vehicles")
public class VehicleEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="code")
	private String code;
	
	@Column(name="monthly_cost")
	private long monthlyCost;
	
	@Column(name="type")
	private int type;
	
	@Column(name="card_type")
	private int cardType;
	
	@ManyToOne
	@JoinColumn(name="project_id")
	private ProjectsEntity project;
	
	@Column(name="updated")
	private Long updated;
	
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public long getMonthlyCost() {
		return monthlyCost;
	}

	public void setMonthlyCost(long monthlyCost) {
		this.monthlyCost = monthlyCost;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public ProjectsEntity getProject() {
		return project;
	}

	public void setProject(ProjectsEntity project) {
		this.project = project;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
	
}
