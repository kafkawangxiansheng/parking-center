package com.spm.common.enums;

public enum SettingAdminType {
	NORMAL (1),
	MONTHLY(2);
	
	int id;
	private  SettingAdminType(int id) {
		this.id = id;
	}
	public int getId() {
		return id;
	}
}
