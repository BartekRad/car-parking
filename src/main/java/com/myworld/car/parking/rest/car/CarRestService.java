package com.myworld.car.parking.rest.car;

import com.myworld.car.parking.domain.car.CarService;
import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.rest.car.requests.CarDTO;
import com.myworld.car.parking.rest.car.requests.CreateCarRequest;
import com.myworld.car.parking.rest.car.requests.UpdateCarRequest;
import com.myworld.car.parking.utils.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CarRestService {

	private final CarRequestConverter converter = new CarRequestConverter();

	private final Validator validator;

	private final CarService carService;

	CarDTO getCar(String registration) {
		CarRegistrationNumber registrationNumber = new CarRegistrationNumber(registration);
		return converter.convert(carService.gerCarOtThrow(registrationNumber));
	}

	void createCar(CreateCarRequest createCarRequest) {
		Car car = converter.convert(createCarRequest);
		carService.createCar(car);
	}

	void updateCar(String registration, UpdateCarRequest updateCarRequest) {
		CarRegistrationNumber registrationNumber = new CarRegistrationNumber(registration);
		validator.validate(registrationNumber);
		Car car = converter.convert(registrationNumber, updateCarRequest);
		carService.updateCar(car);
	}

	void deleteByRegistration(String registration) {
		CarRegistrationNumber registrationNumber = new CarRegistrationNumber(registration);
		validator.validate(registrationNumber);
		carService.deleteCar(registrationNumber);
	}
}
