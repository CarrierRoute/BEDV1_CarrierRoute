package com.grepp.carrierroute.flight.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class FlightSearchRequestDto {
    private String departureCity;
    private LocalDate departureDate;
    private String arrivalCity;
    private LocalDate arrivalDate;
    private String cabinClass;
    private Long headCount;
}
