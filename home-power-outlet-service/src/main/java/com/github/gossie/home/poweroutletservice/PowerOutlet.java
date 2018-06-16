package com.github.gossie.home.poweroutletservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class PowerOutlet {

    @Id
    @GeneratedValue
    private Long id;

    private boolean enabled;
}
