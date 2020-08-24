package com.myworld.car.parking.rest

import com.myworld.car.parking.persistence.model.ParkingSpotEntity

class TestParkingSpots {
    public static ParkingSpotEntity PARKING_SPOT_ENTITY = createParkingSpotEntity()

    private static ParkingSpotEntity createParkingSpotEntity() {
        return ParkingSpotEntity.builder()
                .id(1L)
                .number("NX-19")
                .name("Back")
                .build();
    }
}
