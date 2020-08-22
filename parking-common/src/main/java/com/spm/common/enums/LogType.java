package com.spm.common.enums;

public enum LogType {
	CREATE (1),
	UPDATE(2),
	DElETE(3);
	
	int id;
	private  LogType(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
