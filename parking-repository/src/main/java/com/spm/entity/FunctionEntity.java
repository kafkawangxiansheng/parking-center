package com.spm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="functions")
public class FunctionEntity {
	
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="project_id")
	private Long projectId;
	
	@Column(name="function_id")
	private String functionId;
	
	@Column(name="function_name")
	private String functionName;
	
	@Column(name="function_sec")
	private String functionSec;
	
	@Column(name="updated")
	private Long updated;
	
	@Column(name="disabled")
	private int disabled;

	@Column(name="last_sync")
	private Long lastSync;
	
	@Column(name="deleted")
	private int deleted;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getFunctionSec() {
		return functionSec;
	}

	public void setFunctionSec(String functionSec) {
		this.functionSec = functionSec;
	}

	public Long getUpdated() {
		return updated;
	}

	public void setUpdated(Long updated) {
		this.updated = updated;
	}

	public int getDisabled() {
		return disabled;
	}

	public void setDisabled(int disabled) {
		this.disabled = disabled;
	}

	public Long getLastSync() {
		return lastSync;
	}

	public void setLastSync(Long lastSync) {
		this.lastSync = lastSync;
	}

	public int getDeleted() {
		return deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	
	
	
}
