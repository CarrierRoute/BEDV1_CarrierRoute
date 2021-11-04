package com.grepp.carrierroute.flight.dto;

import lombok.Builder;
import lombok.Getter;
import java.util.List;

@Builder
@Getter
public class FlightsSearchResponseDto {
    private List<FlightOnewaySearchResponseDto> onewayData;
    private FlightRoundSearchResponseDto roundData;
}
