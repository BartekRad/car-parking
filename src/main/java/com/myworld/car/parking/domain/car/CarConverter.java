package com.myworld.car.parking.domain.car;

import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.domain.car.model.CarBrand;
import com.myworld.car.parking.domain.car.model.CarModel;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.persistence.model.CarEntity;

class CarConverter {

	CarEntity convert(Car source) {
		return CarEntity.builder()
				.registration(source.getRegistrationNumber().getInternal())
				.brand(source.getBrand().getInternal())
				.model(source.getModel().getInternal())
				.build();
	}

	Car convert(CarEntity source) {
		return new Car(
				new CarRegistrationNumber(source.getRegistration()),
				new CarBrand(source.getBrand()),
				new CarModel(source.getModel())
		);
	}

}
