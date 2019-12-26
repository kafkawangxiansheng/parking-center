package com.spm.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.spm.dto.ResultObject;

@ControllerAdvice(basePackages="jp.co.sanei.mainsystem.**")
public class CustomExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final @ResponseBody ResultObject<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		//TODO: Log all error information into file
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		ResultObject<ErrorDetails> resultObject = new ResultObject<>();
		resultObject.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resultObject.setData(errorDetails);
		return resultObject;
	}
	
	@ExceptionHandler(UnauthorizedException.class)
	public String handleMyException(UnauthorizedException ex,
	                             HttpServletRequest request,
	                             HttpServletResponse response) {
	    String redirect = "redirect:/login";
	    return redirect;
	}

	@ExceptionHandler(RuntimeException.class)
	public final @ResponseBody ResultObject<ErrorDetails> handleUserNotFoundException(RuntimeException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		ResultObject<ErrorDetails> resultObject = new ResultObject<>();
		resultObject.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		resultObject.setData(errorDetails);
		return resultObject;
	}

}
