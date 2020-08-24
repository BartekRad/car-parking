package com.myworld.car.parking.rest.reservation;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.reservation.model.ReservationTime;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.rest.reservation.requests.CreateReservationRequest;
import com.myworld.car.parking.rest.reservation.requests.ReservationDTO;
import java.time.Clock;
import java.time.LocalDateTime;

class ReservationRequestConverter {

	Reservation convert(CreateReservationRequest request, Clock clock) {
		return new Reservation(
				new CarRegistrationNumber(request.getCarRegistration()),
				new ParkingSpotNumber(request.getParkingSpotNumber()),
				LocalDateTime.now(clock),
				new ReservationTime(
						request.getFromDateTime(),
						request.getToDateTime()
				)
		);
	}

	ReservationDTO convert(Reservation reservation) {
		return ReservationDTO.builder()
				.carRegistration(reservation.getCarRegistration().getInternal())
				.parkingSpotNumber(reservation.getParkingSpotNumber().getInternal())
				.createDate(reservation.getCreateDate())
				.fromDateTime(reservation.getReservationTime().getFromDateTime())
				.toDateTime(reservation.getReservationTime().getToDateTime())
				.build();
	}

}
