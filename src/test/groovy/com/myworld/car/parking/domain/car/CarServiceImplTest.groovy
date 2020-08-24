package com.myworld.car.parking.domain.car

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.myworld.car.parking.ExceptionCodes
import com.myworld.car.parking.domain.car.model.CarBrand
import com.myworld.car.parking.domain.car.model.CarModel
import com.myworld.car.parking.domain.car.model.CarRegistrationNumber
import com.myworld.car.parking.persistence.repo.CarRepository
import com.myworld.car.parking.utils.exceptions.CarParkingException
import com.myworld.car.parking.utils.validation.Validator
import spock.lang.Specification

class CarServiceImplTest extends Specification {

    @Collaborator
    Validator validator = Stub()

    @Collaborator
    CarRepository carRepository = Mock()

    @Subject
    CarServiceImpl carService

    def "should get car"() {
        given:
        def registration = "WI 1234 X"

        when:
        carRepository.findByRegistration(registration) >> Optional.of(TestCars.CAR_ENTITY)
        def result = carService.gerCar(new CarRegistrationNumber(registration))

        then:
        result.isPresent()
        result.get().registrationNumber == new CarRegistrationNumber(registration)
        result.get().brand == new CarBrand("BMW")
        result.get().model == new CarModel("M4")
    }

    def "should get car and not throw exception"() {
        given:
        def registration = "WI 1234 X"

        when:
        carRepository.findByRegistration(registration) >> Optional.of(TestCars.CAR_ENTITY)
        def result = carService.gerCarOtThrow(new CarRegistrationNumber(registration))

        then:
        result.registrationNumber == new CarRegistrationNumber(registration)
        result.brand == new CarBrand("BMW")
        result.model == new CarModel("M4")
    }


    def "should throw car not found exception"() {
        given:
        def registration = "WI 1234 X"

        when:
        carRepository.findByRegistration(registration) >> Optional.empty()
        carService.gerCarOtThrow(new CarRegistrationNumber(registration))

        then:
        CarParkingException ex = thrown()
        ex.exceptionCode == ExceptionCodes.CAR_NOT_FOUND
    }

    def "should create a car"() {
        given:
        def car = TestCars.CAR

        when:
        carService.createCar(car)

        then:
        1 * carRepository.save({
            it.registration == "WI 1234 X" &&
                    it.brand == "BMW" &&
                    it.model == "M4"
        })

    }

    def "should update a car"() {
        given:
        def car = TestCars.CAR

        when:
        carRepository.findByRegistration("WI 1234 X") >> Optional.of(TestCars.CAR_ENTITY)
        carService.updateCar(car)

        then:
        1 * carRepository.save({
            it.registration == "WI 1234 X" &&
                    it.brand == "BMW" &&
                    it.model == "M4"
        })
    }

    def "should delete car by registration"() {
        given:
        def registration = "WI 1234 X"

        when:
        carService.deleteCar(new CarRegistrationNumber(registration))

        then:
        1 * carRepository.deleteByRegistration({ it == registration })
    }

}
