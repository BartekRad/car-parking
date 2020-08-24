package com.myworld.car.parking.rest;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

	@Bean
	public Clock clock() {
		return Clock.fixed(Instant.parse("2018-08-19T00:00:00.00Z"), ZoneOffset.UTC);
	}

}
