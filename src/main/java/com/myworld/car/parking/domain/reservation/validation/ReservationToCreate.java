package com.myworld.car.parking.domain.reservation.validation;

import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.utils.validation.ValidationObject;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReservationToCreate implements ValidationObject {

	@NonNull
	private final Reservation reservation;

}
