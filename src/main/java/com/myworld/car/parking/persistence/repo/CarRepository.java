package com.myworld.car.parking.persistence.repo;

import com.myworld.car.parking.persistence.model.CarEntity;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {

	Optional<CarEntity> findByRegistration(String registration);

	@Transactional
	void deleteByRegistration(String registration);
}
