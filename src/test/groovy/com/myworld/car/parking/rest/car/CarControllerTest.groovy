package com.myworld.car.parking.rest.car

import com.fasterxml.jackson.databind.ObjectMapper
import com.myworld.car.parking.ExceptionCodes
import com.myworld.car.parking.domain.car.TestCars
import com.myworld.car.parking.persistence.model.CarEntity
import com.myworld.car.parking.persistence.repo.CarRepository
import com.myworld.car.parking.rest.car.requests.CreateCarRequest
import com.myworld.car.parking.rest.car.requests.UpdateCarRequest
import com.myworld.car.parking.utils.exceptions.ErrorDTO
import com.myworld.car.parking.utils.exceptions.FieldError
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Stepwise

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Stepwise
@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CarControllerTest extends Specification {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CarRepository carRepository

    def "should create car"() {
        given:
        CreateCarRequest request = CreateCarRequest.builder()
                .registration("WI 1234 X")
                .brand("BMW")
                .model("M4")
                .build()

        when:
        mockMvc.perform(post("/cars")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())

        then:
        CarEntity car = carRepository.findByRegistration("WI 1234 X").get()
        car.registration == "WI 1234 X"
        car.brand == "BMW"
        car.model == "M4"
    }

    def "should not create a car with the same registration"() {
        given:
        CreateCarRequest request = CreateCarRequest.builder()
                .registration("WI 1234 X")
                .brand("BMW")
                .model("M4")
                .build()
        carRepository.save(TestCars.CAR_ENTITY)

        when:
        ErrorDTO result =
                objectMapper.readValue(mockMvc.perform(post("/cars")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                        .andExpect(status().isBadRequest())
                        .andReturn()
                        .response.contentAsString, ErrorDTO.class)

        then:
        result.exceptionCode == ExceptionCodes.VALIDATION_FAILED
        result.fieldErrors == [new FieldError("registration", "Registration number already exists")]
    }

    def "should get a car"() {
        given:
        def registration = "WI 1234 X"
        carRepository.save(TestCars.CAR_ENTITY)

        when:
        String response = mockMvc.perform(get("/cars/")
                .param("registration", registration)
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn()
                .response.contentAsString

        then:

        response == objectMapper.writeValueAsString(TestCars.CAR_DTO)

    }

    def "should update a car"() {
        given:
        def registration = "WI 1234 X"
        UpdateCarRequest request = UpdateCarRequest.builder()
                .brand("Audi")
                .model("RS8")
                .build()
        carRepository.save(TestCars.CAR_ENTITY)

        when:
        mockMvc.perform(put("/cars")
                .param("registration", registration)
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())

        then:
        CarEntity car = carRepository.findByRegistration("WI 1234 X").get()
        car.registration == "WI 1234 X"
        car.brand == "Audi"
        car.model == "RS8"
    }

    def "should delete a car"() {
        given:
        def registration = "WI 1234 X"
        carRepository.save(TestCars.CAR_ENTITY)

        when:
        mockMvc.perform(delete("/cars")
                .param("registration", registration)
                .contentType("application/json"))
                .andExpect(status().isOk())

        then:
        !carRepository.findByRegistration("WI 1234 X").isPresent()

    }

    def cleanup() {
        carRepository.deleteAll()
    }

}
