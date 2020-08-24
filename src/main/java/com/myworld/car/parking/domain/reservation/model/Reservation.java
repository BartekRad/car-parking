package com.myworld.car.parking.domain.reservation.model;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Reservation {

	@NonNull
	private final CarRegistrationNumber carRegistration;

	@NonNull
	private final ParkingSpotNumber parkingSpotNumber;

	@NonNull
	private final LocalDateTime createDate;

	@NonNull
	private final ReservationTime reservationTime;
}
