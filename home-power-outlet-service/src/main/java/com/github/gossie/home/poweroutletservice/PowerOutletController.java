package com.github.gossie.home.poweroutletservice;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PowerOutletController {

    @GetMapping("/power-outlets")
    public List<PowerOutlet> getPowerOutlets() {
        return Collections.emptyList();
    }
}
