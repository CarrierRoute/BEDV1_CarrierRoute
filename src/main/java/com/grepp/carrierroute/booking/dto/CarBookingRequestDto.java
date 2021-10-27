package com.grepp.carrierroute.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CarBookingRequestDto {

    private String carId;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
