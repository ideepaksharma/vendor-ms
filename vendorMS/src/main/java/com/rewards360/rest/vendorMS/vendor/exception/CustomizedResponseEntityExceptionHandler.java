package com.rewards360.rest.vendorMS.vendor.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorInfo> handleAllException(Exception ex, WebRequest request) throws Exception {
		ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(VendorNotFoundException.class)
	public final ResponseEntity<ErrorInfo> handleVendorNotFoundException(Exception ex, WebRequest request)
			throws Exception {
		ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), ex.getMessage(), request.getDescription(false));
		return new ResponseEntity<ErrorInfo>(errorInfo, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), ex.getFieldError().getDefaultMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorInfo, HttpStatus.BAD_REQUEST);
	}

}
