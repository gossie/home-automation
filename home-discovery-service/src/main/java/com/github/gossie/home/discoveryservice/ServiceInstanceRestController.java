package com.github.gossie.home.discoveryservice;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class ServiceInstanceRestController {

    private final DiscoveryClient discoveryClient;

    @RequestMapping("/service-instances/{applicationName}")
    public List<ServiceInstance> serviceInstancesByApplicationName(@PathVariable String applicationName) {
        return discoveryClient.getInstances(applicationName);
    }

    @RequestMapping("/service-instance-names")
    public List<String> serviceInstanceNames() {
        return discoveryClient.getServices();
    }

    @RequestMapping("/service-instances")
    public List<ServiceInstance> serviceInstances() {
        return discoveryClient.getServices().stream()
                .map(discoveryClient::getInstances)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }
}
