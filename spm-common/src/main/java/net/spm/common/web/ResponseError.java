package net.spm.common.web;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by NhanNguyen
 */
public class ResponseError {
	
	@JsonProperty("Code")
	private int statusCode;
	
	@JsonProperty("Message")
	private String message;

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public ResponseError(int statusCode, String message) {
		this.statusCode = statusCode;
		this.message = message;
	}
	
}
