package com.github.gossie.home.roomservice;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@RequiredArgsConstructor
@MessageEndpoint
public class MessageHandler {

    private final RoomRepository roomRepository;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void handleRoom(String roomName) {
        roomRepository.save(new Room(null, roomName));
    }
}
