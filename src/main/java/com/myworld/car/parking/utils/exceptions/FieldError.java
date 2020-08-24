package com.myworld.car.parking.utils.exceptions;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class FieldError {

	private final String field;

	private final String error;

}
