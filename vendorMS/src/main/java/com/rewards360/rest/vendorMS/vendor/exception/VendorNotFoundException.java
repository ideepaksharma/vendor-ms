package com.rewards360.rest.vendorMS.vendor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VendorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public VendorNotFoundException(String message) {
		super(message);
	}

}
