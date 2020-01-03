package com.spm.common.enums;

public enum UserAttributeType {
	ROLE (1),
	PROJECT(2);
	
	int id;
	private  UserAttributeType(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
