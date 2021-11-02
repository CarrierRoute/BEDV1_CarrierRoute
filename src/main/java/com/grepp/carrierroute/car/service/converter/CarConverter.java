package com.grepp.carrierroute.car.service.converter;

import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import org.springframework.stereotype.Component;

@Component
public class CarConverter {
    public CarResponseDto convertCarResponseDto(Car car) {
        return CarResponseDto.builder()
                .id(car.getId())
                .grade(car.getGrade())
                .image(car.getUploadFile())
                .maxPassengers(car.getMaxPassengers())
                .price(car.getPrice())
                .build();

    }
}
