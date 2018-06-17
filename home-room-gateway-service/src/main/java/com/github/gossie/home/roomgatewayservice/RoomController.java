package com.github.gossie.home.roomgatewayservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<String> getRoomNames() {
        return roomService.determineRoomNames();
    }

}
