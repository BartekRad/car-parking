package com.myworld.car.parking.domain.spot;

import com.myworld.car.parking.ExceptionCodes;
import com.myworld.car.parking.domain.spot.model.ParkingSpot;
import com.myworld.car.parking.domain.spot.model.ParkingSpotNumber;
import com.myworld.car.parking.persistence.repo.ParkingSpotRepository;
import com.myworld.car.parking.utils.exceptions.CarParkingException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingSpotServiceImpl implements ParkingSpotService {

	private final ParkingSpotConverter converter = new ParkingSpotConverter();

	private final ParkingSpotRepository parkingSpotRepository;

	@Override
	public Optional<ParkingSpot> getParkingSpot(ParkingSpotNumber number) {
		return parkingSpotRepository.findByNumber(number.getInternal())
				.map(converter::convert);
	}

	@Override
	public ParkingSpot getParkingSpotOrThrow(ParkingSpotNumber number) {
		return parkingSpotRepository.findByNumber(number.getInternal())
				.map(converter::convert)
				.orElseThrow(() -> new CarParkingException(
						"Parking spot with number " + number.getInternal() + " does not exist!",
						ExceptionCodes.PARKING_SPOT_NOT_FOUND,
						HttpStatus.BAD_REQUEST
				));
	}
}
