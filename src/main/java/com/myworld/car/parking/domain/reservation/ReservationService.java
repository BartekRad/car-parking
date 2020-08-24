package com.myworld.car.parking.domain.reservation;

import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import java.util.List;

public interface ReservationService {

	void createReservation(Reservation reservation);

	List<Reservation> getReservations(ParkingSpotNumber parkingSpotNumber);

}
