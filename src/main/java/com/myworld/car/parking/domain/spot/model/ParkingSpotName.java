package com.myworld.car.parking.domain.spot.model;

import com.myworld.car.parking.utils.validation.ValidationObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ParkingSpotName implements ValidationObject {

	@NonNull
	private final String internal;

}
