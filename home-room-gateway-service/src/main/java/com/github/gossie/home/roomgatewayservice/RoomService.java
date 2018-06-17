package com.github.gossie.home.roomgatewayservice;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "determineDefaultNames")
    public List<String> determineRoomNames() {
        ParameterizedTypeReference<Resources<Resource<Room>>> ptr = new ParameterizedTypeReference<Resources<Resource<Room>>>() {};
        return restTemplate.exchange("http://room-service/rooms", HttpMethod.GET, null, ptr)
                .getBody()
                .getContent()
                .stream()
                .map(Resource::getContent)
                .map(Room::getName)
                .collect(Collectors.toList());
    }

    private List<String> determineDefaultNames() {
        return Collections.emptyList();
    }
}
