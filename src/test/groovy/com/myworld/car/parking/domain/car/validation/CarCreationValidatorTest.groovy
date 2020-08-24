package com.myworld.car.parking.domain.car.validation

import com.blogspot.toomuchcoding.spock.subjcollabs.Collaborator
import com.blogspot.toomuchcoding.spock.subjcollabs.Subject
import com.myworld.car.parking.domain.car.TestCars
import com.myworld.car.parking.persistence.model.CarEntity
import com.myworld.car.parking.persistence.repo.CarRepository
import com.myworld.car.parking.utils.exceptions.FieldError
import com.myworld.car.parking.utils.exceptions.ValidationException
import com.myworld.car.parking.utils.validation.ValidationObjectType
import spock.lang.Specification

class CarCreationValidatorTest extends Specification {

    @Collaborator
    CarRepository carRepository = Stub()

    @Subject
    CarCreationValidator validator

    def "should not throw validation exception"() {
        given:
        def car = TestCars.CAR

        when:
        carRepository.findByRegistration("WI 1234 X") >> Optional.empty()
        validator.validate(new CarToCreate(car))

        then:
        noExceptionThrown()
    }


    def "should throw validation exception"() {
        given:
        def car = TestCars.CAR

        when:
        carRepository.findByRegistration("WI 1234 X") >> Optional.of(new CarEntity())
        validator.validate(new CarToCreate(car))

        then:
        ValidationException ex = thrown()
        ex.fieldErrors == [new FieldError("registration", "Registration number already exists")]
    }

    def "should return car to create type"() {
        when:
        def result = validator.getType()

        then:
        result == new ValidationObjectType(CarToCreate.class)
    }

}
