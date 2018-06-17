package com.github.gossie.home.poweroutletgatewayservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hystrix")
public class TestEndpoint {

    private final TestService testService;

    @GetMapping
    public String test(@RequestParam boolean fail) {
        return testService.test(fail);
    }
}
