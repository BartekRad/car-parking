package com.myworld.car.parking.domain.car

import com.myworld.car.parking.domain.car.model.Car
import com.myworld.car.parking.domain.car.model.CarBrand
import com.myworld.car.parking.domain.car.model.CarModel
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber
import com.myworld.car.parking.persistence.model.CarEntity
import com.myworld.car.parking.rest.car.requests.CarDTO

class TestCars {

    public static Car CAR = createCar()
    public static CarEntity CAR_ENTITY = createCarEntity()
    public static CarDTO CAR_DTO = createCarDTO()

    private static Car createCar() {
        new Car(
                new CarRegistrationNumber("WI 1234 X"),
                new CarBrand("BMW"),
                new CarModel("M4")
        )
    }

    private static CarEntity createCarEntity() {
        CarEntity.builder()
                .registration("WI 1234 X")
                .brand("BMW")
                .model("M4")
                .build()
    }

    private static CarDTO createCarDTO() {
        CarDTO.builder()
                .registration("WI 1234 X")
                .brand("BMW")
                .model("M4")
                .build()
    }
}
