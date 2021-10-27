package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class CarBookingResponseDto {

    private final String id;
    private String image;
    private String grade;
    private int price;
    private int maxPassengers;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
