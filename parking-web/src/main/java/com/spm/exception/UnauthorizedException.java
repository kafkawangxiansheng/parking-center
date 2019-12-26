package com.spm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.UNAUTHORIZED, reason="Unauthorized")
public class UnauthorizedException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2365012850207913438L;

	public UnauthorizedException(String message){
		super(message);
	}

}
