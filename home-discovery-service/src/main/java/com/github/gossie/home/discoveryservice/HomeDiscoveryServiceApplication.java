package com.github.gossie.home.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HomeDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeDiscoveryServiceApplication.class, args);
	}
}
