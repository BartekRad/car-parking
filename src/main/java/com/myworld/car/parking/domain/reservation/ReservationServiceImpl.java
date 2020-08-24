package com.myworld.car.parking.domain.reservation;

import com.myworld.car.parking.ExceptionCodes;
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber;
import com.myworld.car.parking.domain.reservation.model.Reservation;
import com.myworld.car.parking.domain.reservation.validation.ReservationToCreate;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.persistence.model.CarEntity;
import com.myworld.car.parking.persistence.model.ParkingSpotEntity;
import com.myworld.car.parking.persistence.model.ReservationEntity;
import com.myworld.car.parking.persistence.repo.CarRepository;
import com.myworld.car.parking.persistence.repo.ParkingSpotRepository;
import com.myworld.car.parking.persistence.repo.ReservationRepository;
import com.myworld.car.parking.utils.exceptions.CarParkingException;
import com.myworld.car.parking.utils.validation.Validator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

	private final Validator validator;

	private final CarRepository carRepository;

	private final ReservationConverter converter = new ReservationConverter();

	private final ParkingSpotRepository parkingSpotRepository;

	private final ReservationRepository reservationRepository;

	@Override
	@Transactional(isolation = Isolation.SERIALIZABLE)
	public void createReservation(Reservation reservation) {
		validator.validate(new ReservationToCreate(reservation));
		ParkingSpotEntity parkingSpotEntity = getParkingSpotOrThrow(reservation.getParkingSpotNumber());
		CarEntity carEntity = getCarOrThrow(reservation.getCarRegistration());
		ReservationEntity reservationEntity = ReservationEntity.builder()
				.car(carEntity)
				.parkingSpot(parkingSpotEntity)
				.fromDate(reservation.getReservationTime().getFromDateTime())
				.toDate(reservation.getReservationTime().getToDateTime())
				.createDate(reservation.getCreateDate())
				.build();
		reservationRepository.save(reservationEntity);
	}

	@Override
	public List<Reservation> getReservations(ParkingSpotNumber parkingSpotNumber) {
		ParkingSpotEntity parkingSpotEntity = getParkingSpotOrThrow(parkingSpotNumber);
		return reservationRepository.findByParkingSpot(parkingSpotEntity)
				.stream()
				.map(converter::convert)
				.collect(Collectors.toList());
	}

	private ParkingSpotEntity getParkingSpotOrThrow(ParkingSpotNumber parkingSpotNumber) {
		return parkingSpotRepository.findByNumber(parkingSpotNumber.getInternal())
				.orElseThrow(() -> new CarParkingException(
						"Parking spot with number " + parkingSpotNumber.getInternal() + " does not exist!",
						ExceptionCodes.PARKING_SPOT_NOT_FOUND,
						HttpStatus.BAD_REQUEST
				));
	}

	private CarEntity getCarOrThrow(CarRegistrationNumber registration) {
		return carRepository.findByRegistration(registration.getInternal())
				.orElseThrow(() -> new CarParkingException(
						"Car with registration " + registration.getInternal() + " does not exist!",
						ExceptionCodes.CAR_NOT_FOUND,
						HttpStatus.BAD_REQUEST
				));
	}
}
