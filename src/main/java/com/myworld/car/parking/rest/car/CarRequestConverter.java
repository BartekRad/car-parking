package com.myworld.car.parking.rest.car;

import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.domain.car.model.CarBrand;
import com.myworld.car.parking.domain.car.model.CarModel;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.rest.car.requests.CreateCarRequest;
import com.myworld.car.parking.rest.car.requests.CarDTO;
import com.myworld.car.parking.rest.car.requests.UpdateCarRequest;

class CarRequestConverter {

	CarDTO convert(Car car) {
		return CarDTO.builder()
				.registration(car.getRegistrationNumber().getInternal())
				.brand(car.getBrand().getInternal())
				.model(car.getModel().getInternal())
				.build();
	}

	Car convert(CreateCarRequest createCarRequest) {
		return new Car(
				new CarRegistrationNumber(createCarRequest.getRegistration()),
				new CarBrand(createCarRequest.getBrand()),
				new CarModel(createCarRequest.getModel())
		);
	}

	Car convert(CarRegistrationNumber registrationNumber, UpdateCarRequest updateCarRequest) {
		return new Car(
				registrationNumber,
				new CarBrand(updateCarRequest.getBrand()),
				new CarModel(updateCarRequest.getModel())
		);
	}
}
