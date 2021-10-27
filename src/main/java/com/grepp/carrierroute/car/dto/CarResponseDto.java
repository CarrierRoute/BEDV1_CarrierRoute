package com.grepp.carrierroute.car.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarResponseDto {

    private final String id;
    private String image;
    private String grade;
    private int price;
    private int maxPassengers;

}
