package com.github.gossie.home.poweroutletgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@EnableZuulProxy
@EnableCircuitBreaker
@SpringBootApplication
public class PowerOutletGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PowerOutletGatewayServiceApplication.class, args);
	}

	@LoadBalanced
	@Bean
    public RestTemplate restTemplate() {
	    return new RestTemplate();
    }
}
