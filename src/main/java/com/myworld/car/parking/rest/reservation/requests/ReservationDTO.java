package com.myworld.car.parking.rest.reservation.requests;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ReservationDTO {

	private final String carRegistration;

	private final String parkingSpotNumber;

	private final LocalDateTime createDate;

	private final LocalDateTime fromDateTime;

	private final LocalDateTime toDateTime;
}
