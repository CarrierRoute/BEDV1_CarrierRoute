package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.HotelBookingDetailsDto;
import com.grepp.carrierroute.booking.dto.HotelBookingRequestDto;
import com.grepp.carrierroute.booking.dto.HotelBookingResponseDto;
import com.grepp.carrierroute.booking.service.HotelBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelBookingController {
    private final HotelBookingService hotelBookingService;

    @PostMapping("/bookings/hotels")
    public HotelBookingResponseDto bookRooms(@RequestBody HotelBookingRequestDto bookingRequestDto, @CookieValue(value = "userId") String userId){
        return hotelBookingService.bookRooms(bookingRequestDto, userId);
    }

    @GetMapping("/bookings/hotels")
    public List<HotelBookingDetailsDto> getHotelBookings(@CookieValue(value = "userId") String userId){
        return hotelBookingService.getHotelBookings(userId);
    }

    @GetMapping("/bookings/hotels/{bookingId}")
    public HotelBookingDetailsDto getHotelBooking(@PathVariable Long bookingId){
        return hotelBookingService.getHotelBooking(bookingId);
    }
}
