package com.grepp.carrierroute.booking.service.converter;

import com.grepp.carrierroute.booking.dto.*;
import com.grepp.carrierroute.flight.domain.Flight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FlightBookingConverter {
    public FlightBookingResponseDto convertFlightBookingResponseDtoByOneway(List<Flight> selectedFlights) {
        return FlightBookingResponseDto.builder()
                .departureFlights(convertOnewayFlightBookingResponseDtos(selectedFlights))
                .arrivalFlights(new ArrayList<>())
                .build();
    }

    public FlightBookingResponseDto convertFlightBookingResponseDtoByRound(FlightBookingResponseDto flightBookingResponseDto, List<Flight> selectedFlights) {
        return FlightBookingResponseDto.builder()
                .departureFlights(flightBookingResponseDto.getDepartureFlights())
                .arrivalFlights(convertOnewayFlightBookingResponseDtos(selectedFlights))
                .build();
    }

    private List<OnewayFlightBookingResponseDto> convertOnewayFlightBookingResponseDtos(List<Flight> selectedFlights) {
        return selectedFlights.stream().map(this::convertOnewayFlightBookingResponseDto).collect(Collectors.toList());
    }

    private OnewayFlightBookingResponseDto convertOnewayFlightBookingResponseDto(Flight flight) {
        return OnewayFlightBookingResponseDto.builder()
                .bookingId(flight.getId())
                .departureCity(flight.getDepartureCity())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDateTime(flight.getArrivalDateTime())
                .airplaneId(flight.getAirplaneSeat().getAirplane().getId())
                .cabinClass(flight.getAirplaneSeat().getCabinClass().name())
                .cost(flight.getCost())
                .build();
    }

    public BookedFlightResponseDto convertBookedFlightResponseDto(Flight bookedFlight) {
        return BookedFlightResponseDto.builder()
                .bookingId(bookedFlight.getId())
                .departureCity(bookedFlight.getDepartureCity())
                .departureDateTime(bookedFlight.getDepartureDateTime())
                .arrivalCity(bookedFlight.getArrivalCity())
                .airplaneId(bookedFlight.getAirplaneSeat().getAirplane().getId())
                .cabinClass(bookedFlight.getAirplaneSeat().getCabinClass().name())
                .airlineName(bookedFlight.getAirplaneSeat().getAirplane().getAirline().getName())
                .cost(bookedFlight.getCost())
                .seatNum(bookedFlight.getAirplaneSeat().getSeatNum())
                .build();

    }

    public List<BookedFlightResponseDto> convertBookedFlightResponseDtos(List<Flight> bookedFlights) {
        return bookedFlights.stream()
                .map(this::convertBookedFlightResponseDto)
                .collect(Collectors.toList());
    }

    public CancelBookedFlightDto convertCancelBookedFlightDto(Long bookingId) {
        return CancelBookedFlightDto.builder()
                .bookingId(bookingId)
                .build();
    }
}
