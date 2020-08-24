package com.myworld.car.parking.rest.car;

import com.myworld.car.parking.rest.car.requests.CarDTO;
import com.myworld.car.parking.rest.car.requests.CreateCarRequest;
import com.myworld.car.parking.rest.car.requests.UpdateCarRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("cars")
@RequiredArgsConstructor
public class CarController {

	private final CarRestService carRestService;

	@GetMapping
	public CarDTO getCar(@RequestParam("registration") @NotBlank String registration) {
		return carRestService.getCar(registration);
	}

	@PostMapping
	public void createCar(@Valid @RequestBody CreateCarRequest createCarRequest) {
		carRestService.createCar(createCarRequest);
	}

	@PutMapping
	public void updateCar(@RequestParam("registration") @NotBlank String registration, @Valid @RequestBody UpdateCarRequest updateCarRequest) {
		carRestService.updateCar(registration, updateCarRequest);
	}

	@DeleteMapping
	public void deleteCar(@RequestParam("registration") @NotBlank String registration) {
		carRestService.deleteByRegistration(registration);
	}
}
