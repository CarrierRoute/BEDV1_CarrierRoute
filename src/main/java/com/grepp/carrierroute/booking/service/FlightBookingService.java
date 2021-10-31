package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.FlightBooking;
import com.grepp.carrierroute.booking.dto.BookedFlightResponseDto;
import com.grepp.carrierroute.booking.dto.CancelBookedFlightDto;
import com.grepp.carrierroute.booking.dto.FlightBookingRequestDto;
import com.grepp.carrierroute.booking.dto.FlightBookingResponseDto;
import com.grepp.carrierroute.booking.repository.FlightBookingRepository;
import com.grepp.carrierroute.booking.service.converter.FlightBookingConverter;
import com.grepp.carrierroute.flight.domain.CabinClass;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.repository.FlightCabinClassRepository;
import com.grepp.carrierroute.flight.repository.FlightRepository;
import com.grepp.carrierroute.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class FlightBookingService {

    private final FlightBookingRepository flightBookingRepository;
    private final FlightCabinClassRepository flightCabinClassRepository;
    private final FlightRepository flightRepository;
    private final UserRepository userRepository;
    private final FlightBookingConverter flightBookingConverter;
    private static final String USERID = "USER-1";

    public FlightBookingService(FlightBookingRepository flightBookingRepository, FlightCabinClassRepository flightCabinClassRepository,
                                FlightRepository flightRepository, UserRepository userRepository,
                                FlightBookingConverter flightBookingConverter) {
        this.flightBookingRepository = flightBookingRepository;
        this.flightCabinClassRepository = flightCabinClassRepository;
        this.flightBookingConverter = flightBookingConverter;
        this.flightRepository = flightRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public FlightBookingResponseDto bookFlight(FlightBookingRequestDto flightBookingRequestDto) {
        FlightBooking flightBooking = flightBookingConverter.convertFlightBooking(flightCabinClassRepository.findByFlightAndCabinClass(flightRepository.findById(flightBookingRequestDto.getFlightId()).get(),
                CabinClass.valueOf(flightBookingRequestDto.getCabinClass())), userRepository.findById(USERID).get());
//        // 예외 처리 필요
//        if(flightBooking.getFlightCabinClass().getSeatCount()>0){
//            // 예외 처리
//        }
        return flightBookingConverter.convertFlightBookingResponseDto(flightBookingRepository.save(flightBooking));
    }

    public BookedFlightResponseDto getBookedFlight(Long bookingId) {
        return flightBookingConverter.convertBookedFlightResponseDto(flightBookingRepository.findById(bookingId).get());
    }

    public List<BookedFlightResponseDto> getBookedFlights() {
        List<FlightBooking> flightBookings = flightBookingRepository.findAllByUser(userRepository.findById(USERID).get());

        List<BookedFlightResponseDto> bookedFlightResponseDtos = new ArrayList<>();
        for (FlightBooking flightBooking : flightBookings) {
            bookedFlightResponseDtos.add(flightBookingConverter.convertBookedFlightResponseDto(flightBooking));
        }
        return bookedFlightResponseDtos;
    }

    public CancelBookedFlightDto cancelBookedFlight(Long bookingId) {
        flightBookingRepository.delete(flightBookingRepository.getById(bookingId));
        return flightBookingConverter.convertCancelBookedFlightDto(bookingId);
    }
}
