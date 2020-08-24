package com.myworld.car.parking.domain.car;

import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import java.util.Optional;

public interface CarService {

	Optional<Car> gerCar(CarRegistrationNumber registrationNumber);

	Car gerCarOtThrow(CarRegistrationNumber registrationNumber);

	void createCar(Car car);

	void updateCar(Car car);

	void deleteCar(CarRegistrationNumber registrationNumber);

}
