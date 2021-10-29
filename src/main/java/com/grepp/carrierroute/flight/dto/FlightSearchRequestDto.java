package com.grepp.carrierroute.flight.dto;

import com.grepp.carrierroute.flight.domain.CabinClass;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.Column;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
public class FlightSearchRequestDto {
    private String departureCity;
    private String arrivalCity;
    private LocalDate departureDate;
    private String cabinClass;
    private Long headCount;
}
