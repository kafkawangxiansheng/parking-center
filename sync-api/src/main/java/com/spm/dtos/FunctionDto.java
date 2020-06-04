package com.spm.dtos;

public class FunctionDto {
	
	private Long id;
	
	private Long projectId;
	
	private String functionId;
	
	private String functionName;
	
	private String functionSec;
	
	private Long updated;
	
	private int disabled;

	private Long lastSync;
	
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
