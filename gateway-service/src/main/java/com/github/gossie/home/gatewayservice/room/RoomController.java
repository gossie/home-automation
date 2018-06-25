package com.github.gossie.home.gatewayservice.room;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/rooms")
public class RoomController {

    private final RoomService roomService;

    @GetMapping
    public List<String> getRoomNames() {
        return roomService.determineRoomNames();
    }

    @PostMapping
    public ResponseEntity<?> createRoom(@RequestBody final Room room) {
        roomService.createRoom(room);
        return ResponseEntity.accepted().build();
    }
}