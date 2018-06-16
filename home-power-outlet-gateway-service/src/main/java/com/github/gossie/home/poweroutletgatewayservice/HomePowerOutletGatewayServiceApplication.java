package com.github.gossie.home.poweroutletgatewayservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableZuulProxy
@EnableDiscoveryClient
@SpringBootApplication
public class HomePowerOutletGatewayServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomePowerOutletGatewayServiceApplication.class, args);
	}

	@Bean
    public RestTemplate restTemplate() {
	    return new RestTemplate();
    }
}
