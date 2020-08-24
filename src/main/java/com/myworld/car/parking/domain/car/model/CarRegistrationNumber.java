package com.myworld.car.parking.domain.car.model;

import com.myworld.car.parking.utils.validation.ValidationObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CarRegistrationNumber implements ValidationObject {

	@NonNull
	private final String internal;

}
