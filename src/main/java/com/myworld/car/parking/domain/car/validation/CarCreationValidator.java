package com.myworld.car.parking.domain.car.validation;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.persistence.repo.CarRepository;
import com.myworld.car.parking.utils.exceptions.FieldError;
import com.myworld.car.parking.utils.exceptions.ValidationException;
import com.myworld.car.parking.utils.validation.ConcreteValidator;
import com.myworld.car.parking.utils.validation.ValidationObjectType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarCreationValidator implements ConcreteValidator<CarToCreate> {

	private final CarRepository carRepository;

	@Override
	public void validate(CarToCreate request) {
		validateRegistrationNumberNotExists(request.getCar().getRegistrationNumber());
	}

	@Override
	public ValidationObjectType getType() {
		return new ValidationObjectType(CarToCreate.class);
	}

	private void validateRegistrationNumberNotExists(CarRegistrationNumber registration) {
		if (carRepository.findByRegistration(registration.getInternal()).isPresent()) {
			FieldError fieldError = new FieldError("registration", "Registration number already exists");
			String message = "Registration number: " + registration.getInternal() + " already exists!";
			throw new ValidationException(message, fieldError);
		}
	}
}
