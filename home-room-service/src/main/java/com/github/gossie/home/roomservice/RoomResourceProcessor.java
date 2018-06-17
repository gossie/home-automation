package com.github.gossie.home.roomservice;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RoomResourceProcessor implements ResourceProcessor<Resource<Room>> {

    @Override
    public Resource<Room> process(Resource<Room> roomResource) {
        return roomResource;
    }
}
