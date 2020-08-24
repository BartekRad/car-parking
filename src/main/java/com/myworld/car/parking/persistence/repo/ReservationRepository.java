package com.myworld.car.parking.persistence.repo;

import com.myworld.car.parking.persistence.model.ParkingSpotEntity;
import com.myworld.car.parking.persistence.model.ReservationEntity;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {

	List<ReservationEntity> findByParkingSpotAndFromDateBeforeAndToDateAfter(ParkingSpotEntity parkingSpot,
			LocalDateTime fromDateBefore,
			LocalDateTime toDateAfter);

	List<ReservationEntity> findByParkingSpot(ParkingSpotEntity parkingSpot);
}
