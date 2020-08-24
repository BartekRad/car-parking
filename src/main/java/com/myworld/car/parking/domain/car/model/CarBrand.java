package com.myworld.car.parking.domain.car.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class CarBrand {

	@NonNull
	private final String internal;

}
