package com.grepp.carrierroute.flight.controller;

import com.grepp.carrierroute.flight.dto.FlightSearchRequestDto;
import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import com.grepp.carrierroute.flight.service.FlightService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public List<FlightSearchResponseDto> getFlights(@RequestBody FlightSearchRequestDto flightSearchRequestDto){
        return flightService.findFlightsBy(flightSearchRequestDto);
    }

    @GetMapping("/flights/{flightId}")
    public FlightSearchResponseDto getFlight(@PathVariable Long flightId){
        return flightService.findFlightBy(flightId);
    }
}
