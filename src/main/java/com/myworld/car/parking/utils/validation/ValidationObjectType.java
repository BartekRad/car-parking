package com.myworld.car.parking.utils.validation;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode
public class ValidationObjectType {

	private final Class<? extends ValidationObject> clazz;

	public ValidationObjectType(Class<? extends ValidationObject> clazz) {
		this.clazz = clazz;
	}
}


