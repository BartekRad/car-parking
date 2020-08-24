package com.myworld.car.parking.rest.car.requests;

import com.myworld.car.parking.utils.validation.ValidationObject;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class UpdateCarRequest implements ValidationObject {

	@NotBlank
	private final String brand;

	@NotBlank
	private final String model;
}
