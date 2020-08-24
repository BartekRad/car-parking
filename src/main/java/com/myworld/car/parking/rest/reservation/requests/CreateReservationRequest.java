package com.myworld.car.parking.rest.reservation.requests;

import com.myworld.car.parking.utils.validation.ValidationObject;
import java.time.LocalDateTime;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateReservationRequest implements ValidationObject {

	@NotBlank
	private final String carRegistration;

	@NotBlank
	private final String parkingSpotNumber;

	@NonNull
	private final LocalDateTime fromDateTime;

	@NonNull
	private final LocalDateTime toDateTime;
}
