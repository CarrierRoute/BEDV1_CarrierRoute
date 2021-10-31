package com.grepp.carrierroute.flight.repository;

import com.grepp.carrierroute.flight.domain.CabinClass;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.domain.FlightCabinClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightCabinClassRepository extends JpaRepository<FlightCabinClass,Long> {
    public FlightCabinClass findByFlightAndCabinClass(Flight flight, CabinClass cabinClass);
}
