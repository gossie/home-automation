package com.github.gossie.home.roomservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path="/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    @GetMapping
    public List<Room> getRooms() {
        return roomRepository.findAll();
    }
}
