package com.grepp.carrierroute.car.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarResponseDto {

    private final String id;
    private String image;
    private CarGrade grade;
    private int price;
    private int maxPassengers;

}
