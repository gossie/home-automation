package com.github.gossie.home.poweroutletservice;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PowerOutletController {

    private final PowerOutletRepository powerOutletRepository;

    @GetMapping(value = "/power-outlets")
    public List<PowerOutlet> getPowerOutlets() {
        return powerOutletRepository.findAll();
    }

    @GetMapping(value = "/power-outlets", params = {"enabled"})
    public List<PowerOutlet> getPowerOutletsByStatus(@RequestParam boolean enabled) {
        return powerOutletRepository.findByEnabled(enabled);
    }
}
