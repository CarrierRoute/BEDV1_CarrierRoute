package com.grepp.carrierroute.flight.dto;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
@Builder
public class FlightSearchResponseDto {
    private String departureCity;
    private LocalDateTime departureDateTime;
    private String arrivalCity;
    private LocalDateTime arrivalDateTime;
    private Long airplaneId;
    private long cost;
}
