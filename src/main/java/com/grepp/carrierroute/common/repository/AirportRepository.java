package com.grepp.carrierroute.common.repository;

import com.grepp.carrierroute.common.domain.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport,Long> {
}
