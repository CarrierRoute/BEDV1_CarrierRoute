package com.grepp.carrierroute.car.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import lombok.Getter;

@Getter
public class CarCreationDto {

    private String licencePlate;
    private CarGrade grade;
    private int price;
    private int maxPassengers;
    private Long carCompanyId;
    private Long airportId;
}
