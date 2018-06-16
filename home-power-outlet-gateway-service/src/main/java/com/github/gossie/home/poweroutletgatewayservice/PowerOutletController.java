package com.github.gossie.home.poweroutletgatewayservice;

import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "power-outlets")
public class PowerOutletController {

    private final DiscoveryClient discoveryClient;
    private final RestTemplate restTemplate;

    @GetMapping(path = "/status")
    @HystrixCommand(fallbackMethod = "determineDefaultPowerOutlets")
    public List<PowerOutlet> getPowerOutlets() {
        URI uri = discoveryClient.getInstances("power-outlet-service").get(0).getUri();

        ParameterizedTypeReference<List<PowerOutlet>> ptr = new ParameterizedTypeReference<List<PowerOutlet>>() {};

        return restTemplate.exchange("http://power-outlet-service/powerOutlets", HttpMethod.GET, null, ptr)
                .getBody()
                .stream()
                .collect(Collectors.toList());
    }

    private List<PowerOutlet> determineDefaultPowerOutlets() {
        return Collections.emptyList();
    }
}
