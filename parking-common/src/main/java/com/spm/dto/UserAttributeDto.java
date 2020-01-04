package com.spm.dto;

import com.spm.common.enums.UserAttributeType;

public class UserAttributeDto {

	private UserAttributeType type;

	private String value;

	public UserAttributeType getType() {
		return type;
	}

	public void setType(UserAttributeType type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
}
