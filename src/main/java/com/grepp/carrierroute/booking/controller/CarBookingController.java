package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CarBookingController {

    private final CarBookingService carBookingService;

    @PostMapping("/cars/bookings")
    public CarBookingResponseDto bookCar(CarBookingRequestDto carBookingRequestDto) {
        return carBookingService.bookCar(carBookingRequestDto);
    }

    @GetMapping("/profile/bookings/cars")
    public List<CarBookingResponseDto> getCarBookings(){
        return carBookingService.getCarBookings();
    }


}
