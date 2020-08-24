package com.myworld.car.parking.domain.car.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Car {

	private final @NonNull CarRegistrationNumber registrationNumber;

	private final @NonNull CarBrand brand;

	private final @NonNull CarModel model;

}
