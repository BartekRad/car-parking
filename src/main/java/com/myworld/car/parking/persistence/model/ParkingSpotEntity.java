package com.myworld.car.parking.persistence.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
@Table(name = "parking_spot")
public class ParkingSpotEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "parkingSpotSequenceGenerator")
	@GenericGenerator(
			name = "parkingSpotSequenceGenerator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@Parameter(name = "sequence_name", value = "PARKING_SPOT_ID_SEQ")})
	private Long id;

	@NotBlank
	private String name;

	@NotBlank
	@Column(unique = true)
	private String number;

}
