package com.github.gossie.home.poweroutletservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class PowerOutletServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerOutletServiceApplication.class, args);
	}
}
