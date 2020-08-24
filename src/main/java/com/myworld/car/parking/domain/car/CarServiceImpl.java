package com.myworld.car.parking.domain.car;

import com.myworld.car.parking.ExceptionCodes;
import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.car.validation.CarToCreate;
import com.myworld.car.parking.persistence.model.CarEntity;
import com.myworld.car.parking.persistence.repo.CarRepository;
import com.myworld.car.parking.utils.exceptions.CarParkingException;
import com.myworld.car.parking.utils.validation.Validator;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CarServiceImpl implements CarService {

	private final Validator validator;

	private final CarConverter converter = new CarConverter();

	private final CarRepository carRepository;

	@Override
	public Optional<Car> gerCar(CarRegistrationNumber registrationNumber) {
		return carRepository.findByRegistration(registrationNumber.getInternal())
				.map(converter::convert);
	}

	@Override
	public Car gerCarOtThrow(CarRegistrationNumber registrationNumber) {
		return converter.convert(getCarOrThrow(registrationNumber));
	}

	@Override
	public void createCar(Car car) {
		validator.validate(new CarToCreate(car));
		CarEntity carEntity = converter.convert(car);
		carRepository.save(carEntity);
	}

	@Override
	public void updateCar(Car car) {
		CarEntity carEntity = getCarOrThrow(car.getRegistrationNumber());
		carEntity.setBrand(car.getBrand().getInternal());
		carEntity.setModel(car.getModel().getInternal());
		carRepository.save(carEntity);
	}

	@Override
	public void deleteCar(CarRegistrationNumber registrationNumber) {
		carRepository.deleteByRegistration(registrationNumber.getInternal());
	}

	private CarEntity getCarOrThrow(CarRegistrationNumber registration) {
		return carRepository.findByRegistration(registration.getInternal())
				.orElseThrow(() -> new CarParkingException(
						"Car with registration " + registration.getInternal() + " does not exist!",
						ExceptionCodes.CAR_NOT_FOUND,
						HttpStatus.BAD_REQUEST
				));
	}
}
