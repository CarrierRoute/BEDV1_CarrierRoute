package com.grepp.carrierroute.flight.service.converter;

import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import org.springframework.stereotype.Component;

@Component
public class FlightConverter {

    public FlightSearchResponseDto convertFlightSearchResponseDto(Flight flight,Long flightCost) {
        return FlightSearchResponseDto.builder()
                .flightId(flight.getId())
                .departureCity(flight.getDepartureCity())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDateTime(flight.getArrivalDateTime())
                .flightCost(flightCost)
                .build();
    }
}
