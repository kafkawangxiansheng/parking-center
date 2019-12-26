package com.spm.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.spm.dto.ResultObject;

@SuppressWarnings({ "unchecked", "rawtypes" })
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final @ResponseBody ResultObject<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
		//TODO: Log all error information into file
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		ResultObject resultObject = new ResultObject<>();
		resultObject.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		resultObject.setData(errorDetails);
		return resultObject;
	}

	@ExceptionHandler(RuntimeException.class)
	public final @ResponseBody ResultObject<ErrorDetails> handleUserNotFoundException(RuntimeException ex,
			WebRequest request) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
		ResultObject resultObject = new ResultObject<>();
		resultObject.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		resultObject.setData(errorDetails);
		return resultObject;
	}

	// error handle for @Valid
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("timestamp", new Date());
		body.put("status", status.value());
		// Get all errors
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
		body.put("data", errors);
		
		return new ResponseEntity<>(body, headers, status);

	}

}
