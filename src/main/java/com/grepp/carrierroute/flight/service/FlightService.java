package com.grepp.carrierroute.flight.service;

import com.grepp.carrierroute.flight.domain.CabinClass;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.dto.FlightSearchRequestDto;
import com.grepp.carrierroute.flight.dto.FlightSearchResponseDto;
import com.grepp.carrierroute.flight.repository.FlightCabinClassRepository;
import com.grepp.carrierroute.flight.repository.FlightRepository;
import com.grepp.carrierroute.flight.service.converter.FlightConverter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class FlightService {

    private final FlightRepository flightRepository;
    private final FlightCabinClassRepository flightCabinClassRepository;
    private final FlightConverter flightConverter;

    public FlightService(FlightRepository flightRepository, FlightCabinClassRepository flightCabinClassRepository, FlightConverter flightConverter) {
        this.flightRepository = flightRepository;
        this.flightCabinClassRepository = flightCabinClassRepository;
        this.flightConverter = flightConverter;
    }

    public List<FlightSearchResponseDto> findFlightsBy(FlightSearchRequestDto flightSearchRequestDto) {
        List<Flight> flights = findFlightsBy(flightSearchRequestDto.getDepartureCity(),flightSearchRequestDto.getDepartureDate(),
                                            flightSearchRequestDto.getArrivalCity(),flightSearchRequestDto.getHeadCount(),
                                            flightSearchRequestDto.getCabinClass());
        return flights.stream()
                        .map(flight -> flightConverter.convertFlightSearchResponseDto(flight,
                                                                                    flight.findFlightCabinClassBy(CabinClass.valueOf(flightSearchRequestDto.getCabinClass())).getSeatCost()))
                        .collect(Collectors.toList());
    }

    private List<Flight> findFlightsBy(String departureCity, LocalDate departureDate, String arrivalCity, Long headCount, String cabinClass) {
        List<Long> flightIds = flightRepository.findFlightsBy(arrivalCity,departureDate,departureCity,headCount,cabinClass);
        return flightIds.stream()
                        .map(flightId -> flightRepository.findById(flightId).get())
                        .collect(Collectors.toList());
    }

    public FlightSearchResponseDto findFlightBy(Long flightId) {
        return flightConverter.convertFlightSearchResponseDto(flightRepository.findById(flightId).get(),
                                                            flightCabinClassRepository.findById(flightId).get().getSeatCost());
    }
}
