package com.grepp.carrierroute.flight.repository;

import com.grepp.carrierroute.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight,Long> {
}
