package com.grepp.carrierroute.car.dto;

import com.grepp.carrierroute.car.domain.CarGrade;
import com.grepp.carrierroute.common.file.UploadFile;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CarResponseDto {

    private final Long id;
    private UploadFile image;
    private CarGrade grade;
    private int price;
    private int maxPassengers;

}
