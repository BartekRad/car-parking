package com.myworld.car.parking.rest.reservation.validators;

import com.myworld.car.parking.rest.reservation.requests.CreateReservationRequest;
import com.myworld.car.parking.utils.exceptions.FieldError;
import com.myworld.car.parking.utils.exceptions.ValidationException;
import com.myworld.car.parking.utils.validation.ConcreteValidator;
import com.myworld.car.parking.utils.validation.ValidationObjectType;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;

@Service
public class CreateReservationRequestValidator implements ConcreteValidator<CreateReservationRequest> {

	@Override
	public void validate(CreateReservationRequest request) {
		validateFromDateBeforeAfter(request.getFromDateTime(), request.getToDateTime());
	}

	private void validateFromDateBeforeAfter(LocalDateTime fromDateTime, LocalDateTime toDateTime) {
		if (!fromDateTime.isBefore(toDateTime)) {
			FieldError fieldError = new FieldError("startDate", "Start date should be before end date");
			throw new ValidationException("Start date should be before end date!", fieldError);
		}
	}

	@Override
	public ValidationObjectType getType() {
		return new ValidationObjectType(CreateReservationRequest.class);
	}
}
