package com.grepp.carrierroute.booking.controller;

import com.grepp.carrierroute.booking.dto.BookedFlightResponseDto;
import com.grepp.carrierroute.booking.dto.CancelBookedFlightDto;
import com.grepp.carrierroute.booking.dto.FlightBookingRequestDto;
import com.grepp.carrierroute.booking.dto.FlightBookingResponseDto;
import com.grepp.carrierroute.booking.service.FlightBookingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FlightBookingController {

    private final FlightBookingService flightBookingService;

    public FlightBookingController(FlightBookingService flightBookingService){
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("/bookings/flight")
    public FlightBookingResponseDto bookFlight(@RequestBody FlightBookingRequestDto flightBookingRequestDto){
        return flightBookingService.bookFlight(flightBookingRequestDto);
    }

    @GetMapping("/bookings/flight/{bookingId}")
    public BookedFlightResponseDto getBookedFlight(@PathVariable Long bookingId){
        return flightBookingService.getBookedFlight(bookingId);
    }

    @GetMapping("/bookings/flight")
    public List<BookedFlightResponseDto> getBookedFlight(){
        return flightBookingService.getBookedFlights();
    }

    @DeleteMapping("/bookings/flight/{bookingId}")
    public CancelBookedFlightDto cancelBookedFlight(@PathVariable Long bookingId){
        return flightBookingService.cancelBookedFlight(bookingId);
    }
}
