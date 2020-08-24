package com.myworld.car.parking.domain.spot.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class ParkingSpot {

	@NonNull
	private final ParkingSpotNumber parkingSpotNumber;

	@NonNull
	private final ParkingSpotName parkingSpotName;

}
