package com.github.gossie.home.gatewayservice.poweroutlet;

import com.github.gossie.home.gatewayservice.room.Room;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PowerOutlet {

    private boolean enabled;
    private Room room;
}
