package com.grepp.carrierroute.flight.controller;

import com.grepp.carrierroute.flight.dto.*;
import com.grepp.carrierroute.flight.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class FlightController {
    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping("/flights")
    public ResponseEntity<FlightsSearchResponseDto> getFlights(@RequestParam("searchTypeName") SearchType searchType, @RequestBody FlightSearchRequestDto flightSearchRequestDto){
        return new ResponseEntity<>(flightService.getFlights(searchType,flightSearchRequestDto), HttpStatus.OK);
    }

    @GetMapping("/flights/{flightId}")
    public ResponseEntity<FlightSearchResponseDto> getFlight(@PathVariable Long flightId){
        return new ResponseEntity<>(flightService.getFlight(flightId), HttpStatus.OK);
    }
}
