package com.grepp.carrierroute.flight.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class FlightsSearchResponseDto {
    private List<FlightOnewaySearchResponseDto> onewayData;
    private List<FlightRoundSearchResponseDto> roundData;
}
