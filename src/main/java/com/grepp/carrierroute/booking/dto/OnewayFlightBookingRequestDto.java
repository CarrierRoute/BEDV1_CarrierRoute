package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class OnewayFlightBookingRequestDto {
    private String departureCity;
    private LocalDateTime departureDateTime;
    private String arrivalCity;
    private LocalDateTime arrivalDateTime;
    private String cabinClass;
    private int headCount;
    private Long airplaneId;
    private long cost;
}
