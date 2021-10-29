package com.grepp.carrierroute.booking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CarBookingSimple {
    private Long id;
    private String carLicencePlate;
    private String image;
    private String place;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
