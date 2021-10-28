package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CarBookingController {

    private final CarBookingService carBookingService;

    @PostMapping("/bookings/cars")
    public CarBookingResponseDto bookCar(CarBookingRequestDto carBookingRequestDto) {
        return carBookingService.bookCar(carBookingRequestDto);
    }

    @GetMapping("/bookings/cars")
    public List<CarBookingResponseDto> getCarBookings(){
        return carBookingService.getCarBookings();
    }

    @GetMapping("/bookings/cars/{bookingId}")
    public CarBookingResponseDto getCarBooking(@PathVariable Long bookingId) {
        return carBookingService.getCarBooking(bookingId);
    }

    @DeleteMapping("/bookings/cars/{bookingId}")
    public ResponseEntity<Long> cancelBooking(@PathVariable Long bookingId) {
        carBookingService.cancelBooking(bookingId);
        return new ResponseEntity<>(bookingId,HttpStatus.OK);
    }
}
