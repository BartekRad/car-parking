package com.myworld.car.parking.domain.spot;

import com.myworld.car.parking.domain.spot.model.ParkingSpot;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import java.util.Optional;

public interface ParkingSpotService {

	Optional<ParkingSpot> getParkingSpot(ParkingSpotNumber number);

	ParkingSpot getParkingSpotOrThrow(ParkingSpotNumber number);

}
