package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class CarBookingSimple {
    private Long id;
    private String carId;
    private String image;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
