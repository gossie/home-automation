package com.github.gossie.home.poweroutletservice;

import static com.github.gossie.home.poweroutletservice.ResourceAssert.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.springframework.hateoas.Resource;

public class PowerOutletResourceProcessorTest {

    private PowerOutletResourceProcessor powerOutletResourceProcessor;

    @Before
    public void setUp() {
        powerOutletResourceProcessor = new PowerOutletResourceProcessor();
    }

    @Test
    public void testProcess() {
        Resource<PowerOutlet> resource = new Resource<>(new PowerOutlet(1L, true, 5L));
        Resource<PowerOutlet> processedResource = powerOutletResourceProcessor.process(resource);

        assertThat(processedResource).hasLink("room", "http://room-service/rooms/5");
    }

}