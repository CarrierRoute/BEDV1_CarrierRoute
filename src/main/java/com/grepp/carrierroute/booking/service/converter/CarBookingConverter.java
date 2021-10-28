package com.grepp.carrierroute.booking.service.converter;

import com.grepp.carrierroute.booking.domain.CarBooking;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.car.domain.Car;
import org.springframework.stereotype.Component;

@Component
public class CarBookingConverter {

    public CarBookingResponseDto convertCarBookingResponseDto(CarBooking carBooking, Car car) {
        return CarBookingResponseDto.builder()
                .id(carBooking.getId())
                .carId(car.getId())
                .grade(car.getGrade())
                .image(car.getImage())
                .maxPassengers(car.getMaxPassengers())
                .price(car.getPrice())
                .startDateTime(carBooking.getStartDateTime())
                .endDateTime(carBooking.getEndDateTime())
                .build();
    }
}
