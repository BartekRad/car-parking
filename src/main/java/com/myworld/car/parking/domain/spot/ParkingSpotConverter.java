package com.myworld.car.parking.domain.spot;

import com.myworld.car.parking.domain.spot.model.ParkingSpot;
import com.myworld.car.parking.domain.spot.model.ParkingSpotName;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.persistence.model.ParkingSpotEntity;

class ParkingSpotConverter {

	ParkingSpot convert(ParkingSpotEntity parkingSpotEntity) {
		return new ParkingSpot(
				new ParkingSpotNumber(parkingSpotEntity.getNumber()),
				new ParkingSpotName(parkingSpotEntity.getName())
		);
	}
}
