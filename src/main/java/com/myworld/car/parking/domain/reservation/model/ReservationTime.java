package com.myworld.car.parking.domain.reservation.model;

import com.google.common.base.Preconditions;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode
public class ReservationTime {

	private final LocalDateTime fromDateTime;

	private final LocalDateTime toDateTime;

	public ReservationTime(@NonNull LocalDateTime fromDateTime, @NonNull LocalDateTime toDateTime) {
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		Preconditions.checkArgument(fromDateTime.isBefore(toDateTime));
	}

}
