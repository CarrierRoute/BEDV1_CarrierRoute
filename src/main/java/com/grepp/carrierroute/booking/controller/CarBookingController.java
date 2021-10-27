package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/car/bookings")
public class CarBookingController {

    private final CarBookingService carBookingService;

    public CarBookingResponseDto bookCar(CarBookingRequestDto carBookingRequestDto) {
        return carBookingService.bookCar(carBookingRequestDto);
    }
}
