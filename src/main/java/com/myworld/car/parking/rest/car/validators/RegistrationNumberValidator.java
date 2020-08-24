package com.myworld.car.parking.rest.car.validators;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.utils.validation.ConcreteValidator;
import com.myworld.car.parking.utils.validation.ValidationObjectType;
import org.springframework.stereotype.Service;

@Service
public class RegistrationNumberValidator implements ConcreteValidator<CarRegistrationNumber> {

	@Override
	public void validate(CarRegistrationNumber object) {
		//Here we could check if the car is stolen : D
	}

	@Override
	public ValidationObjectType getType() {
		return new ValidationObjectType(CarRegistrationNumber.class);
	}
}
