package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.*;
import com.grepp.carrierroute.booking.service.FlightBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    public FlightBookingController(FlightBookingService flightBookingService){
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("/bookings/flights")
    public FlightBookingResponseDto bookFlight(@CookieValue(value = "userId") String userId, @RequestBody FlightBookingRequestDto flightBookingRequestDto){
        return flightBookingService.bookFlight(userId, flightBookingRequestDto);
    }


    @GetMapping("/bookings/flights/{bookingId}")
    public BookedFlightResponseDto getBookedFlight(@PathVariable Long bookingId){
        return flightBookingService.getBookedFlight(bookingId);
    }

    @GetMapping("/bookings/flights")
    public List<BookedFlightResponseDto> getBookedFlights(@CookieValue(value = "userId") String userId){
        return flightBookingService.getBookedFlights(userId);
    }

    @DeleteMapping("/bookings/flights/{bookingId}")
    public CancelBookedFlightDto cancelBookedFlight(@CookieValue(value = "userId") String userId, @PathVariable Long bookingId){
        return flightBookingService.cancelBookedFlight(userId, bookingId);
    }
}
