package com.myworld.car.parking.domain.reservation.validation;

import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.reservation.model.ReservationTime;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.persistence.model.ParkingSpotEntity;
import com.myworld.car.parking.persistence.model.ReservationEntity;
import com.myworld.car.parking.persistence.repo.CarRepository;
import com.myworld.car.parking.persistence.repo.ParkingSpotRepository;
import com.myworld.car.parking.persistence.repo.ReservationRepository;
import com.myworld.car.parking.utils.exceptions.FieldError;
import com.myworld.car.parking.utils.exceptions.ValidationException;
import com.myworld.car.parking.utils.validation.ConcreteValidator;
import com.myworld.car.parking.utils.validation.ValidationObjectType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReservationCreationValidator implements ConcreteValidator<ReservationToCreate> {

	private final CarRepository carRepository;

	private final ParkingSpotRepository parkingSpotRepository;

	private final ReservationRepository reservationRepository;

	@Override
	public void validate(ReservationToCreate reservationToCreate) {
		Reservation reservation = reservationToCreate.getReservation();
		validateCarExists(reservation.getCarRegistration());
		ParkingSpotEntity parkingSpotEntity = validateParkingSpotExists(reservation.getParkingSpotNumber());
		validateParkingSpotIsAvailable(parkingSpotEntity, reservation.getReservationTime());
	}

	private void validateParkingSpotIsAvailable(ParkingSpotEntity parkingSpotEntity, ReservationTime reservationTime) {
		List<ReservationEntity> overLappingReservations =
				reservationRepository.findByParkingSpotAndFromDateBeforeAndToDateAfter(parkingSpotEntity,
						reservationTime.getToDateTime(),
						reservationTime.getFromDateTime());
		if (!overLappingReservations.isEmpty()) {
			FieldError fieldError = new FieldError("parkingSpot", "Parking spot is not available at this time");
			throw new ValidationException("Parking spot is not available at this time", fieldError);
		}
	}

	private ParkingSpotEntity validateParkingSpotExists(ParkingSpotNumber parkingSpotNumber) {
		return parkingSpotRepository.findByNumber(parkingSpotNumber.getInternal()).orElseThrow(() -> {
			FieldError fieldError = new FieldError("parkingSpot", "Parking spot doesn't exist");
			return new ValidationException("Parking spot with number: " + parkingSpotNumber.getInternal() + "doesn't exist", fieldError);
		});
	}

	private void validateCarExists(CarRegistrationNumber carRegistration) {
		if (!carRepository.findByRegistration(carRegistration.getInternal()).isPresent()) {
			FieldError fieldError = new FieldError("car", "Car doesn't exist");
			throw new ValidationException("Car with number: " + carRegistration.getInternal() + "doesn't exist", fieldError);
		}
	}

	@Override
	public ValidationObjectType getType() {
		return new ValidationObjectType(ReservationToCreate.class);
	}
}
