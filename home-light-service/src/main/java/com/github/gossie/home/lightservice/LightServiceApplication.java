package com.github.gossie.home.lightservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class LightServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LightServiceApplication.class, args);
	}
}
