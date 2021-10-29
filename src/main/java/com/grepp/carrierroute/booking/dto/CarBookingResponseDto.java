package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class CarBookingResponseDto {

    private Long id;
    private String carId;
    private String image;
    private CarGrade grade;
    private Integer price;
    private Integer maxPassengers;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
