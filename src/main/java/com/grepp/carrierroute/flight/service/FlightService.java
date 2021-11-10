package com.grepp.carrierroute.flight.service;

import com.grepp.carrierroute.exception.NotFoundException;
import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.dto.FlightSearchRequestDto;
import com.grepp.carrierroute.flight.dto.FlightsSearchResponseDto;
import com.grepp.carrierroute.flight.dto.SearchType;
import com.grepp.carrierroute.flight.repository.FlightRepository;
import com.grepp.carrierroute.flight.service.converter.FlightConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightConverter flightConverter;

    public FlightService(FlightRepository flightRepository, FlightConverter flightConverter) {
        this.flightRepository = flightRepository;
        this.flightConverter = flightConverter;
    }

    public FlightsSearchResponseDto getFlights(SearchType searchType, FlightSearchRequestDto flightSearchRequestDto) {
        return searchType.getFlightSearchType()
                .findFlights(this,flightSearchRequestDto);
    }

    public FlightsSearchResponseDto findFlightsByOneway(FlightSearchRequestDto flightSearchRequestDto) {
        return flightConverter.convertFlightsSearchResponseDtoByOneway(
                flightRepository.findFlightsByOneway(
                        flightSearchRequestDto.getDepartureCity(),
                        flightSearchRequestDto.getDepartureDate(),
                        flightSearchRequestDto.getArrivalCity(),
                        flightSearchRequestDto.getCabinClass()));
    }

    public FlightsSearchResponseDto findFlightsByRound(FlightSearchRequestDto flightSearchRequestDto) {
        List<Flight> departureFlights = flightRepository.findDepartureFlightsByRound(
                flightSearchRequestDto.getDepartureCity(),
                flightSearchRequestDto.getDepartureDate(),
                flightSearchRequestDto.getArrivalCity(),
                flightSearchRequestDto.getCabinClass());

        List<Flight> arrivalFlights = flightRepository.findArrivalFlightsByRound(
                flightSearchRequestDto.getArrivalCity(),
                flightSearchRequestDto.getArrivalDate(),
                flightSearchRequestDto.getDepartureCity(),
                flightSearchRequestDto.getCabinClass());

        return flightConverter.convertFlightsSearchResponseDtoByRound(
                departureFlights,
                arrivalFlights);
    }

    public FlightSearchResponseDto getFlight(Long flightId) {
        return flightConverter.convertFlightSearchResponseDto(findFlightById(flightId));
    }

    private Flight findFlightById(Long flightId) {
        return flightRepository.findById(flightId)
                .orElseThrow(()->new NotFoundException(Flight.class,flightId));
    }
}
