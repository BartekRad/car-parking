package com.myworld.car.parking.utils.exceptions;

import com.myworld.car.parking.ExceptionCodes;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public class CarParkingException extends RuntimeException {

	private final String exceptionCode;

	private final HttpStatus httpStatus;

	private final List<FieldError> fieldErrors;

	public CarParkingException(String message) {
		this(message, ExceptionCodes.UNKNOWN, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public CarParkingException(String message, String exceptionCode, HttpStatus httpStatus) {
		this(message, exceptionCode, httpStatus, Collections.emptyList());
	}

	CarParkingException(String message, String exceptionCode, HttpStatus httpStatus, List<FieldError> fieldErrors) {
		super(message);
		this.exceptionCode = exceptionCode;
		this.httpStatus = httpStatus;
		this.fieldErrors = fieldErrors;
	}

	public String getExceptionCode() {
		return exceptionCode;
	}

	public List<FieldError> getFieldErrors() {
		return fieldErrors;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
}
