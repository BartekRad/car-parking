package com.myworld.car.parking.domain.car.validation;

import com.myworld.car.parking.domain.car.model.Car;
import com.myworld.car.parking.utils.validation.ValidationObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CarToCreate implements ValidationObject {

	@NonNull
	private final Car car;
}
