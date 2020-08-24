package com.myworld.car.parking.persistence.repo;

import com.myworld.car.parking.persistence.model.ParkingSpotEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotEntity, Long> {

	Optional<ParkingSpotEntity> findByNumber(String number);
}
