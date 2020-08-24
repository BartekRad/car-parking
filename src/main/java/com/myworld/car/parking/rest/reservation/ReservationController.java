package com.myworld.car.parking.rest.reservation;

import com.myworld.car.parking.rest.reservation.requests.CreateReservationRequest;
import com.myworld.car.parking.rest.reservation.requests.ReservationDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("reservations")
@RequiredArgsConstructor
public class ReservationController {

	private final ReservationRestService reservationRestService;

	@PostMapping
	public void createReservation(@Valid @RequestBody CreateReservationRequest createReservationRequest) {
		reservationRestService.createReservation(createReservationRequest);
	}

	@GetMapping
	public List<ReservationDTO> getCar(@RequestParam("parkingSpotNumber") @NotBlank String parkingSpotNumber) {
		return reservationRestService.getReservations(parkingSpotNumber);
	}

}
