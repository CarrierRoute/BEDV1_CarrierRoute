package com.grepp.carrierroute.flight.dto;

import lombok.Builder;

import java.util.List;

@Builder
public class FlightRoundSearchResponseDto {
    private List<FlightOnewaySearchResponseDto> departureFlight;
    private List<FlightOnewaySearchResponseDto> arrivalFlight;
}
