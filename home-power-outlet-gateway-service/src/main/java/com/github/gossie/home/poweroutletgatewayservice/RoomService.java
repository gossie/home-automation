package com.github.gossie.home.poweroutletgatewayservice;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
class RoomService {

    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "determineDefaultRoom")
    public Room determineRoom(String href) {
        return restTemplate.getForObject(href, Room.class);
    }

    private Room determineDefaultRoom(String href) {
        return new Room("default in determineDefaultRoom");
    }
}
