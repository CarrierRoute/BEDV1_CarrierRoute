package com.grepp.carrierroute.booking.service.converter;

import com.grepp.carrierroute.booking.domain.FlightBooking;
import com.grepp.carrierroute.booking.dto.BookedFlightResponseDto;
import com.grepp.carrierroute.booking.dto.CancelBookedFlightDto;
import com.grepp.carrierroute.booking.dto.FlightBookingRequestDto;
import com.grepp.carrierroute.booking.dto.FlightBookingResponseDto;
import com.grepp.carrierroute.flight.domain.Airline;
import com.grepp.carrierroute.flight.domain.CabinClass;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.domain.FlightCabinClass;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.stereotype.Component;

@Component
public class FlightBookingConverter {

    public FlightBooking convertFlightBooking(FlightCabinClass flightCabinClass, User user){
        return FlightBooking.builder()
                .flightCabinClass(flightCabinClass)
                .user(user)
                .build();
    }

    public FlightBookingResponseDto convertFlightBookingResponseDto(FlightBooking flightBooking){
        return FlightBookingResponseDto.builder()
                .bookingId(flightBooking.getId())
                .flightId(flightBooking.getId())
                .departureCity(flightBooking.getFlightCabinClass().getFlight().getDepartureCity())
                .departureDateTime(flightBooking.getFlightCabinClass().getFlight().getDepartureDateTime())
                .arrivalCity(flightBooking.getFlightCabinClass().getFlight().getArrivalCity())
                .arrivalDateTime(flightBooking.getFlightCabinClass().getFlight().getArrivalDateTime())
                .flightCost(flightBooking.getFlightCabinClass().getSeatCost())
                .cabinClass(flightBooking.getFlightCabinClass().getCabinClass().name())
                .build();
    }

    public BookedFlightResponseDto convertBookedFlightResponseDto(FlightBooking flightBooking) {
        return BookedFlightResponseDto.builder()
                .bookingId(flightBooking.getId())
                .flightId(flightBooking.getId())
                .departureCity(flightBooking.getFlightCabinClass().getFlight().getDepartureCity())
                .departureDateTime(flightBooking.getFlightCabinClass().getFlight().getDepartureDateTime())
                .arrivalCity(flightBooking.getFlightCabinClass().getFlight().getArrivalCity())
                .arrivalDateTime(flightBooking.getFlightCabinClass().getFlight().getArrivalDateTime())
                .flightCost(flightBooking.getFlightCabinClass().getSeatCost())
                .cabinClass(flightBooking.getFlightCabinClass().getCabinClass().name())
                .build();
    }

    public CancelBookedFlightDto convertCancelBookedFlightDto(Long bookingId) {
        return CancelBookedFlightDto.builder()
                .bookingId(bookingId)
                .build();
    }
}
