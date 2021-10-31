package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FlightBookingRequestDto {
    private Long flightId;
    private String cabinClass;
}
