package com.myworld.car.parking.domain.reservation;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.reservation.model.ReservationTime;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.persistence.model.ReservationEntity;

public class ReservationConverter {

	public Reservation convert(ReservationEntity reservationEntity) {
		return new Reservation(
				new CarRegistrationNumber(reservationEntity.getCar().getRegistration()),
				new ParkingSpotNumber(reservationEntity.getParkingSpot().getNumber()),
				reservationEntity.getCreateDate(),
				new ReservationTime(
						reservationEntity.getFromDate(),
						reservationEntity.getToDate()
				)
		);
	}
}
