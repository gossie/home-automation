package com.github.gossie.home.poweroutletservice;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.assertj.core.api.AbstractAssert;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

public class ResourceAssert extends AbstractAssert<ResourceAssert, Resource<PowerOutlet>> {

    private ResourceAssert(Resource<PowerOutlet> actual) {
        super(actual, ResourceAssert.class);
    }

    public static ResourceAssert assertThat(Resource<PowerOutlet> actual) {
        return new ResourceAssert(actual);
    }

    public ResourceAssert hasLink(String rel, String href) {
        Link link = actual.getLink(rel);
        assertNotNull(link);
        assertEquals(href, link.getHref());
        return this;
    }
}
