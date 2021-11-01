package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import com.grepp.carrierroute.common.file.UploadFile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Builder
@Getter
public class CarBookingResponseDto {

    private Long id;
    private String carLicencePlate;
    private UploadFile image;
    private CarGrade grade;
    private int price;
    private int maxPassengers;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
}
