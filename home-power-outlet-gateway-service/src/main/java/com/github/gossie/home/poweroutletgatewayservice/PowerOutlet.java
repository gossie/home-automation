package com.github.gossie.home.poweroutletgatewayservice;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class PowerOutlet {

    private Long id;
    private boolean status;
}
