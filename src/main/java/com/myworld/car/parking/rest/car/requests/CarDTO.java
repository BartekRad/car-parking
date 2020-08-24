package com.myworld.car.parking.rest.car.requests;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CarDTO {

	private final String registration;

	private final String brand;

	private final String model;
}
