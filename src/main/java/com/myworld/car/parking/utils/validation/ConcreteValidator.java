package com.myworld.car.parking.utils.validation;

public interface ConcreteValidator<OBJECT extends ValidationObject> {

	void validate(OBJECT object);

	ValidationObjectType getType();

}
