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
@Table(name = "car")
public class CarEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carSequenceGenerator")
	@GenericGenerator(
			name = "carSequenceGenerator",
			strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
			parameters = {@Parameter(name = "sequence_name", value = "CAR_ID_SEQ")})
	private Long id;

	@NotBlank
	@Column(unique = true)
	private String registration;

	@NotBlank
	private String brand;

	@NotBlank
	private String model;

}
