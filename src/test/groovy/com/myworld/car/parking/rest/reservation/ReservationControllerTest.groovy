package com.myworld.car.parking.rest.reservation

import com.fasterxml.jackson.databind.ObjectMapper
import com.myworld.car.parking.domain.car.TestCars
import com.myworld.car.parking.persistence.model.ReservationEntity
import com.myworld.car.parking.persistence.repo.CarRepository
import com.myworld.car.parking.persistence.repo.ReservationRepository
import com.myworld.car.parking.rest.TestConfig
import com.myworld.car.parking.rest.TestParkingSpots
import com.myworld.car.parking.rest.reservation.requests.CreateReservationRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Stepwise

import java.time.LocalDateTime

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Stepwise
@ActiveProfiles("test")
@SpringBootTest
@Import(TestConfig.class)
@AutoConfigureMockMvc
class ReservationControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ReservationRepository reservationRepository

    @Autowired
    private CarRepository carRepository


    def "should create reservation"() {
        given:
        carRepository.save(TestCars.CAR_ENTITY)
        CreateReservationRequest request = CreateReservationRequest.builder()
                .carRegistration("WI 1234 X")
                .parkingSpotNumber("NX-10")
                .fromDateTime(LocalDateTime.of(2020, 1, 1, 0, 0))
                .toDateTime(LocalDateTime.of(2020, 1, 1, 2, 2))
                .build()

        when:
        mockMvc.perform(post("/reservations")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())

        then:
        ReservationEntity reservation = reservationRepository.findByParkingSpot(TestParkingSpots.PARKING_SPOT_ENTITY)[0]
        reservation.car.registration == "WI 1234 X"
        reservation.parkingSpot.number == "NX-10"
        reservation.fromDate == LocalDateTime.of(2020, 1, 1, 0, 0)
        reservation.toDate == LocalDateTime.of(2020, 1, 1, 2, 2)
        reservation.createDate == LocalDateTime.of(2018, 8, 19, 0, 0)
    }

    def "should get reservation for a parking spot"() {
    }

    def cleanup() {
        reservationRepository.deleteAll()
        carRepository.deleteAll()
    }
}
