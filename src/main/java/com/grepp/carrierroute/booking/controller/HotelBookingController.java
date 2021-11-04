package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.HotelBookingDetailsDto;
import com.grepp.carrierroute.booking.dto.HotelBookingRequestDto;
import com.grepp.carrierroute.booking.dto.HotelBookingResponseDto;
import com.grepp.carrierroute.booking.service.HotelBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class HotelBookingController {
    private final HotelBookingService hotelBookingService;

    @PostMapping("/bookings/hotels")
    public ResponseEntity<HotelBookingResponseDto> bookRooms(@RequestBody HotelBookingRequestDto bookingRequestDto, @CookieValue(value = "userId") String userId){
        return new ResponseEntity<>(hotelBookingService.bookRooms(bookingRequestDto, userId), HttpStatus.CREATED);
    }

    @GetMapping("/bookings/hotels")
    public ResponseEntity<List<HotelBookingDetailsDto>> getHotelBookings(@CookieValue(value = "userId") String userId){
        List<HotelBookingDetailsDto> hotelBookings = hotelBookingService.getHotelBookings(userId);

        if(hotelBookings.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(hotelBookings, HttpStatus.OK);
    }

    @GetMapping("/bookings/hotels/{bookingId}")
    public ResponseEntity<HotelBookingDetailsDto> getHotelBooking(@PathVariable Long bookingId){
        return new ResponseEntity<>(hotelBookingService.getHotelBooking(bookingId), HttpStatus.OK);
    }

    @DeleteMapping("/bookings/hotels/{bookingId}")
    public ResponseEntity<Long> cancelHotelBooking(@PathVariable Long bookingId, @CookieValue(value = "userId") String userId){
        hotelBookingService.cancelHotelBooking(bookingId, userId);
        return new ResponseEntity<>(bookingId, HttpStatus.OK);
    }
}
