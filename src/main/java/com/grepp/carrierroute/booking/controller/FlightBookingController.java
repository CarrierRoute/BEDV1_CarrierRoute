package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.*;
import com.grepp.carrierroute.booking.service.FlightBookingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    public FlightBookingController(FlightBookingService flightBookingService){
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("/bookings/flights")
    public ResponseEntity<FlightBookingResponseDto> bookFlight(@CookieValue(value = "userId") String userId, @RequestBody FlightBookingRequestDto flightBookingRequestDto){
        return new ResponseEntity<>(flightBookingService.bookFlight(userId, flightBookingRequestDto), HttpStatus.CREATED);
    }


    @GetMapping("/bookings/flights/{bookingId}")
    public ResponseEntity<BookedFlightResponseDto> getBookedFlight(@PathVariable Long bookingId){
        return new ResponseEntity<>(flightBookingService.getBookedFlight(bookingId), HttpStatus.OK);
    }

    @GetMapping("/bookings/flights")
    public ResponseEntity<List<BookedFlightResponseDto>> getBookedFlights(@CookieValue(value = "userId") String userId){
        return new ResponseEntity<>(flightBookingService.getBookedFlights(userId), HttpStatus.OK);
    }

    @DeleteMapping("/bookings/flights/{bookingId}")
    public ResponseEntity<CancelBookedFlightDto> cancelBookedFlight(@CookieValue(value = "userId") String userId, @PathVariable Long bookingId){
        return new ResponseEntity<>(flightBookingService.cancelBookedFlight(userId, bookingId), HttpStatus.OK);
    }
}
