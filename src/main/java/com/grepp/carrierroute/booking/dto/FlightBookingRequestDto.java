package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class FlightBookingRequestDto {
    private FlightBookingType flightBookingType;
    private OnewayFlightBookingRequestDto departureFlight;
    private OnewayFlightBookingRequestDto arrivalFlight;
}
