package com.myworld.car.parking.persistence.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reservation")
public class ReservationEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservationSequenceGenerator")
	@GenericGenerator(
			name = "reservationSequenceGenerator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@Parameter(name = "sequence_name", value = "RESERVATION_ID_SEQ")})
	private Long id;

	@ManyToOne
	@JoinColumn(name = "car_id")
	private CarEntity car;

	@ManyToOne
	@JoinColumn(name = "parking_spot_id")
	private ParkingSpotEntity parkingSpot;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime createDate;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime fromDate;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime toDate;
}
