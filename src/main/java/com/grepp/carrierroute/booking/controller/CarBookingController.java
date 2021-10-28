package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/profile/bookings/cars/{bookingId}")
    public CarBookingResponseDto getCarBooking(@PathVariable Long bookingId) {
        return carBookingService.getCarBooking(bookingId);
    }
}
