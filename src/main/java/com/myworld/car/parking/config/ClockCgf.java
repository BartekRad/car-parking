package com.myworld.car.parking.config;

import java.time.Clock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!test")
@Configuration
public class ClockCgf {

	@Bean
	public Clock clock() {
		return Clock.systemDefaultZone();
	}

}
