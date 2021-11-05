package com.grepp.carrierroute.booking.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.grepp.carrierroute.booking.service.FlightBookingService;
import com.grepp.carrierroute.user.domain.User;
import lombok.Getter;
import java.util.Arrays;

@Getter
public enum FlightBookingType {
    ONEWAY("oneway"){
        @Override
        public FlightBookingResponseDto bookFlight(FlightBookingService flightBookingService, User user, FlightBookingRequestDto flightBookingRequestDto) {
            return flightBookingService.bookOnewayFlight(user, flightBookingRequestDto);
        }
    },
    ROUND("round"){
        @Override
        public FlightBookingResponseDto bookFlight(FlightBookingService flightBookingService, User user, FlightBookingRequestDto flightBookingRequestDto) {
            return flightBookingService.bookRoundFlight(user, flightBookingRequestDto);
        }
    };

    private final String value;

    FlightBookingType(String value){
        this.value = value;
    }

    @JsonCreator
    public static FlightBookingType create(String value){
        return Arrays.stream(FlightBookingType.values())
                .filter(flightBookingType -> flightBookingType.getValue().equals(value.toLowerCase()))
                .findFirst()
                .orElse(ONEWAY);
    }

    public abstract FlightBookingResponseDto bookFlight(FlightBookingService flightBookingService, User user, FlightBookingRequestDto flightBookingRequestDto);
}

