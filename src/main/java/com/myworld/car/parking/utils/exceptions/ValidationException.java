package com.myworld.car.parking.utils.exceptions;

import com.google.common.base.Preconditions;
import com.myworld.car.parking.ExceptionCodes;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;

public class ValidationException extends CarParkingException {

	public ValidationException(String message, FieldError fieldError) {
		this(message, Collections.singletonList(fieldError));
	}

	public ValidationException(List<FieldError> fieldErrors) {
		this("Validation failed", fieldErrors);
		Preconditions.checkArgument(!fieldErrors.isEmpty());
	}

	private ValidationException(String message, List<FieldError> fieldErrors) {
		super(message, ExceptionCodes.VALIDATION_FAILED, HttpStatus.BAD_REQUEST, fieldErrors);
	}

}
