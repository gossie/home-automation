package com.github.gossie.home.gatewayservice.room;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.stream.messaging.Source;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpMethod;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

    private final RestTemplate restTemplate;
    private final Source messageSource;

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

    public void createRoom(final Room room) {
        Message<String> message = MessageBuilder.withPayload(room.getName()).build();
        messageSource.output().send(message);
    }

    @HystrixCommand(fallbackMethod = "determineDefaultRoom")
    public Room determineRoom(String href) {
        return restTemplate.getForObject(href, Room.class);
    }

    private Room determineDefaultRoom(String href) {
        return new Room("default in determineDefaultRoom");
    }

}