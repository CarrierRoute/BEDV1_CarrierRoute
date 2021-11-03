package com.grepp.carrierroute.flight.controller;

import com.grepp.carrierroute.flight.dto.*;
import com.grepp.carrierroute.flight.service.FlightService;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public FlightsSearchResponseDto getFlights(@RequestParam("searchTypeName") SearchType searchType, @RequestBody FlightSearchRequestDto flightSearchRequestDto){
        return flightService.getFlights(searchType,flightSearchRequestDto);
    }

    @GetMapping("/flights/{flightId}")
    public FlightSearchResponseDto getFlight(@PathVariable Long flightId){
        return flightService.getFlight(flightId);
    }
}
