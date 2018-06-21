package com.github.gossie.home.roomservice;

import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
//@Component
public class MessageHandler {

    private final RoomRepository roomRepository;

    @StreamListener(Sink.INPUT)
    public void handleRoom(Room room) {
        roomRepository.save(room);
    }
}
