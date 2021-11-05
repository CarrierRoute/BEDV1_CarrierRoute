package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
public class FlightBookingResponseDto {
    private List<OnewayFlightBookingResponseDto> departureFlights;
    private List<OnewayFlightBookingResponseDto> arrivalFlights;
}
