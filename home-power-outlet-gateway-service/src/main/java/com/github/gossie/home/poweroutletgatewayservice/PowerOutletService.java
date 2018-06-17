package com.github.gossie.home.poweroutletgatewayservice;

import java.util.Collections;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
class PowerOutletService {

    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "determineDefaultPowerOutlets")
    public ResponseEntity<Resources<Resource<PowerOutlet>>> determinePowerOutlets() {
        ParameterizedTypeReference<Resources<Resource<PowerOutlet>>> ptr = new ParameterizedTypeReference<Resources<Resource<PowerOutlet>>>() {};
        return restTemplate.exchange("http://power-outlet-service/powerOutlets", HttpMethod.GET, null, ptr);
    }

    private ResponseEntity<Resources<Resource<PowerOutlet>>> determineDefaultPowerOutlets() {
        return ResponseEntity.ok(Resources.wrap(Collections.emptyList()));
    }
}
