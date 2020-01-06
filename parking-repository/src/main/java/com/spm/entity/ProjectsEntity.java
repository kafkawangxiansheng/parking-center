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
@Table(name = "projects")
public class ProjectsEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="company_id")
	private CompanyEntity company;
	
	@Column(name="name")
	private String name;
	
	@Column(name="address")
	private String address;
	
	@Column(name="disable")
	private boolean disable;
	
	@Column(name = "created")
	private String created;
	
	@Column(name = "updated")
	private String updated;

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getUpdated() {
		return updated;
	}

	public void setUpdated(String updated) {
		this.updated = updated;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

}
