package com.github.gossie.home.poweroutletservice;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@EnableEurekaClient
@SpringBootApplication
public class PowerOutletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerOutletServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner initializeTestData(PowerOutletRepository repository) {
		return args -> {
			PowerOutlet one = new PowerOutlet(null, true, 1L);
			PowerOutlet two = new PowerOutlet(null, false, 1L);
			PowerOutlet three = new PowerOutlet(null, true, 2L);
			PowerOutlet four = new PowerOutlet(null, true, 2L);
			PowerOutlet five = new PowerOutlet(null, true, 3L);
			PowerOutlet six = new PowerOutlet(null, true, 4L);
			repository.saveAll(Arrays.asList(one, two, three, four, five, six));
		};
	}
}
