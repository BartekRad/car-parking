package com.myworld.car.parking.utils.exceptions;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public
class ErrorDTO {

	private final String message;

	private final String exceptionCode;

	private final List<FieldError> fieldErrors;

}
