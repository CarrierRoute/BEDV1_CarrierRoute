package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BookedFlightResponseDto {
    private Long bookingId;
    private String departureCity;
    private LocalDateTime departureDateTime;
    private String arrivalCity;
    private LocalDateTime arrivalDateTime;
    private String cabinClass;
    private Long airplaneId;
    private long cost;
    private String airlineName;
    private int seatNum;
}
