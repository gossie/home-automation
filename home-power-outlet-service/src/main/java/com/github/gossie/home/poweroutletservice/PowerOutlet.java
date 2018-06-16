package com.github.gossie.home.poweroutletservice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Data
public class PowerOutlet {

    @Id
    @GeneratedValue
    private Long id;

    private boolean status;
}
