package com.grepp.carrierroute.flight.service.converter;

import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.dto.FlightOnewaySearchResponseDto;
import com.grepp.carrierroute.flight.dto.FlightRoundSearchResponseDto;
import com.grepp.carrierroute.flight.dto.FlightsSearchResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlightConverter {

    public FlightsSearchResponseDto convertFlightsSearchResponseDtoByOneway(List<Flight> flightsByOneway) {
        return FlightsSearchResponseDto.builder()
                .onewayData(convertFlightOnewaySearchResponseDto(flightsByOneway))
                .roundData(FlightRoundSearchResponseDto.builder().build())
                .build();
    }

    public List<FlightOnewaySearchResponseDto> convertFlightOnewaySearchResponseDto(List<Flight> flightsByOneway){
        List<FlightOnewaySearchResponseDto> flightOnewaySearchResponseDtos = new ArrayList<>();
        flightsByOneway.forEach(flightByOneway -> flightOnewaySearchResponseDtos.add(convertFlightOnewaySearchResponseDto(flightByOneway)));
        return flightOnewaySearchResponseDtos;
    }

    public FlightOnewaySearchResponseDto convertFlightOnewaySearchResponseDto(Flight flight){
        return FlightOnewaySearchResponseDto.builder()
                .departureCity(flight.getDepartureCity())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDateTime(flight.getArrivalDateTime())
                .cost(flight.getCost())
                .airplaneId(flight.getAirplaneSeat().getAirplane().getId())
                .build();
    }

    public FlightsSearchResponseDto convertFlightsSearchResponseDtoByRound(List<Flight> departureFlights, List<Flight> arrivalFlights) {
        return FlightsSearchResponseDto.builder()
                .onewayData(new ArrayList<>())
                .roundData(convertFlightRoundSearchResponseDto(departureFlights, arrivalFlights))
                .build();
    }

    public FlightRoundSearchResponseDto convertFlightRoundSearchResponseDto(List<Flight> departureFlights, List<Flight> arrivalFlights){
        return FlightRoundSearchResponseDto.builder()
                .departureFlight(convertFlightOnewaySearchResponseDto(departureFlights))
                .arrivalFlight(convertFlightOnewaySearchResponseDto(arrivalFlights))
                .build();

    }

    public FlightSearchResponseDto convertFlightSearchResponseDto(Flight flight) {
        return FlightSearchResponseDto.builder()
                .departureCity(flight.getDepartureCity())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDateTime(flight.getArrivalDateTime())
                .airplaneId(flight.getAirplaneSeat().getAirplane().getId())
                .cost(flight.getCost())
                .build();

    }
}
