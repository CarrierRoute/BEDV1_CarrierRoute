package com.grepp.carrierroute.flight.dto;

import com.grepp.carrierroute.flight.service.FlightService;
import lombok.Getter;

import java.util.Arrays;

@Getter
public enum FlightSearchType {
    ONEWAY("oneway"){
        @Override
        public FlightsSearchResponseDto findFlights(FlightService flightService, FlightSearchRequestDto flightSearchRequestDto) {
            return flightService.findFlightsByOneway(flightSearchRequestDto);
        }
    },
    ROUND("round"){
        @Override
        public FlightsSearchResponseDto findFlights(FlightService flightService, FlightSearchRequestDto flightSearchRequestDto) {
            return flightService.findFlightsByRound(flightSearchRequestDto);
        }
    };

    private final String value;

    FlightSearchType(String value){
        this.value = value;
    }

    public static FlightSearchType create(String value){
        return Arrays.stream(FlightSearchType.values())
                .filter(flightSearchType -> flightSearchType.getValue().equals(value.toLowerCase()))
                .findFirst()
                .orElse(ONEWAY);
    }

    public abstract FlightsSearchResponseDto findFlights(FlightService flightService, FlightSearchRequestDto flightSearchRequestDto);
}
