package com.github.gossie.home.poweroutletservice;

import java.net.URI;

import org.springframework.data.rest.webmvc.support.BaseUriLinkBuilder;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class PowerOutletResourceProcessor implements ResourceProcessor<Resource<PowerOutlet>> {

    @Override
    public Resource<PowerOutlet> process(Resource<PowerOutlet> powerOutletResource) {
        PowerOutlet powerOutlet = powerOutletResource.getContent();
        Link roomLink = BaseUriLinkBuilder.create(URI.create("http://room-service/rooms"))
                .slash(powerOutlet.getRoomId())
                .withRel("room");

        powerOutletResource.add(roomLink);

        return powerOutletResource;
    }
}
