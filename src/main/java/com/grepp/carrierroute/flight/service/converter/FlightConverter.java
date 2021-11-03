package com.grepp.carrierroute.flight.service.converter;

import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.dto.FlightOnewaySearchResponseDto;
import com.grepp.carrierroute.flight.dto.FlightRoundSearchResponseDto;
import com.grepp.carrierroute.flight.dto.FlightsSearchResponseDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class FlightConverter {

    public FlightsSearchResponseDto convertFlightsSearchResponseDtoByOneway(List<Flight> flightsByOneway) {
        return FlightsSearchResponseDto.builder()
                .onewayData(convertFlightOnewaySearchResponseDto(flightsByOneway))
                .roundData(new ArrayList<>())
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
                .airlineName(flight.getAirplaneSeat().getAirplane().getAirline().getName())
                .build();
    }

    public FlightsSearchResponseDto convertFlightsSearchResponseDtoByRound(List<Flight> departureFlights, List<Flight> arrivalFlights) {
        return FlightsSearchResponseDto.builder()
                .onewayData(new ArrayList<>())
                .roundData(convertFlightRoundSearchResponseDto(departureFlights, arrivalFlights))
                .build();
    }

    public List<FlightRoundSearchResponseDto> convertFlightRoundSearchResponseDto(List<Flight> departureFlights, List<Flight> arrivalFlights){
        return IntStream.range(0, departureFlights.size()).mapToObj(i -> convertFlightRoundSearchResponseDto(departureFlights.get(i), arrivalFlights.get(i))).collect(Collectors.toList());
    }

    public FlightRoundSearchResponseDto convertFlightRoundSearchResponseDto(Flight departureFlight,Flight arrivalFlight){
        return FlightRoundSearchResponseDto.builder()
                .departureFlight(convertFlightOnewaySearchResponseDto(departureFlight))
                .arrivalFlight(convertFlightOnewaySearchResponseDto(arrivalFlight))
                .build();
    }

    public FlightSearchResponseDto convertFlightSearchResponseDto(Flight flight) {
        return FlightSearchResponseDto.builder()
                .departureCity(flight.getDepartureCity())
                .departureDateTime(flight.getDepartureDateTime())
                .arrivalCity(flight.getArrivalCity())
                .arrivalDateTime(flight.getArrivalDateTime())
                .airlineName(flight.getAirplaneSeat().getAirplane().getName())
                .cost(flight.getCost())
                .build();

    }
}
