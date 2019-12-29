package com.spm.entity.enums;

/**
 * Created by Vincent on 02/10/2018
 */
public enum SettingTypeEnum {
	REPORT(1);
	
	private int reportCode;
	
	private SettingTypeEnum(int code) {
		this.reportCode = code;
	}

	public int getRepotCode() {
		return this.reportCode;
	}
}
