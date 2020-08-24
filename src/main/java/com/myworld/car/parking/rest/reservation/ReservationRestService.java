package com.myworld.car.parking.rest.reservation;

import com.myworld.car.parking.domain.reservation.ReservationService;
import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.rest.reservation.requests.CreateReservationRequest;
import com.myworld.car.parking.rest.reservation.requests.ReservationDTO;
import java.time.Clock;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ReservationRestService {

	private final Clock clock;

	private final ReservationRequestConverter converter = new ReservationRequestConverter();

	private final ReservationService reservationService;

	void createReservation(CreateReservationRequest createReservationRequest) {
		Reservation reservation = converter.convert(createReservationRequest, clock);
		reservationService.createReservation(reservation);
	}

	List<ReservationDTO> getReservations(String number) {
		ParkingSpotNumber parkingSpotNumber = new ParkingSpotNumber(number);
		return reservationService.getReservations(parkingSpotNumber)
				.stream()
				.map(converter::convert)
				.collect(Collectors.toList());
	}
}
