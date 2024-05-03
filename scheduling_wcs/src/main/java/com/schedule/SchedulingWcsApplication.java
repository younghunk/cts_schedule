package com.schedule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulingWcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulingWcsApplication.class, args);
	}

}
