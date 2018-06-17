package com.github.gossie.home.poweroutletgatewayservice;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "power-outlets")
public class PowerOutletController {

    private final PowerOutletService powerOutletService;
    private final RoomService roomService;

    private ExecutorService executorService;

    @PostConstruct
    public void init() {
        executorService = Executors.newFixedThreadPool(4);
    }

    @GetMapping
    public List<PowerOutlet> getPowerOutlets() {
        ResponseEntity<Resources<Resource<PowerOutlet>>> response = powerOutletService.determinePowerOutlets();
        Collection<Resource<PowerOutlet>> content = response.getBody().getContent();
        Map<String, Room> rooms = determineRooms(content);
        return content.stream()
                .peek(resource -> resource.getContent().setRoom(rooms.get(resource.getLink("room").getHref())))
                .map(Resource::getContent)
                .collect(Collectors.toList());
    }

    private Map<String, Room> determineRooms(Collection<Resource<PowerOutlet>> powerOutlets) {
        return powerOutlets.stream()
                .map(resource -> resource.getLink("room"))
                .filter(Objects::nonNull)
                .map(Link::getHref)
                .distinct()
                .map(href -> new Entry<Callable<Room>>(href, () -> roomService.determineRoom(href)))
                .map(entry -> new Entry<>(entry.getKey(), executorService.submit(entry.getValue())))
                .collect(Collectors.toMap(Entry::getKey, this::resolveFuture));
    }

    private Room resolveFuture(Entry<Future<Room>> entry) {
        try {
            return entry.getValue().get();
        } catch(InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @RequiredArgsConstructor
    @Getter
    private static class Entry<V> {

        private final String key;
        private final V value;
    }
}
