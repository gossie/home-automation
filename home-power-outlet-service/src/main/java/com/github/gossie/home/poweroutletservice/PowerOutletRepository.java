package com.github.gossie.home.poweroutletservice;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerOutletRepository extends JpaRepository<PowerOutlet, Long> {

    List<PowerOutlet> findByStatus(boolean status);
}
