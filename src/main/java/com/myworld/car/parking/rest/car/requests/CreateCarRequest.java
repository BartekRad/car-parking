package com.myworld.car.parking.rest.car.requests;

import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateCarRequest {

	@NotBlank
	private final String registration;

	@NotBlank
	private final String brand;

	@NotBlank
	private final String model;
}
