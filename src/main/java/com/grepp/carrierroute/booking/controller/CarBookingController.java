package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.service.CarBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class CarBookingController {

    private final CarBookingService carBookingService;

    @PostMapping("/bookings/cars")
    public ResponseEntity<CarBookingResponseDto> bookCar(@RequestBody CarBookingRequestDto carBookingRequestDto, @CookieValue(value = "userId") String userId) {

        return new ResponseEntity<>(carBookingService.bookCar(carBookingRequestDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/bookings/cars")
    public ResponseEntity<List<CarBookingResponseDto>> getCarBookings(@CookieValue(value = "userId") String userId){
        return new ResponseEntity<>(carBookingService.getCarBookings(userId), HttpStatus.OK);
    }

    @GetMapping("/bookings/cars/{bookingId}")
    public ResponseEntity<CarBookingResponseDto> getCarBooking(@PathVariable Long bookingId) {
        return new ResponseEntity<>(carBookingService.getCarBookingDetail(bookingId), HttpStatus.OK);
    }

    @DeleteMapping("/bookings/cars/{bookingId}")
    public ResponseEntity<Long> cancelBooking(@PathVariable Long bookingId, @CookieValue(value = "userId") String userId) {
        carBookingService.cancelBooking(bookingId, userId);
        return new ResponseEntity<>(bookingId,HttpStatus.OK);
    }
}
