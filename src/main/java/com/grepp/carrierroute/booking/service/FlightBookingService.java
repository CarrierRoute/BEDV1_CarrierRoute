package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.dto.*;
import com.grepp.carrierroute.booking.repository.FlightBookingRepository;
import com.grepp.carrierroute.booking.service.converter.FlightBookingConverter;
import com.grepp.carrierroute.exception.NotFoundException;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.user.domain.User;
import com.grepp.carrierroute.user.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class FlightBookingService {

    private FlightBookingRepository flightBookingRepository;
    private UserRepository userRepository;
    private FlightBookingConverter flightBookingConverter;

    public FlightBookingService(FlightBookingRepository flightBookingRepository, UserRepository userRepository, FlightBookingConverter flightBookingConverter) {
        this.flightBookingRepository = flightBookingRepository;
        this.userRepository = userRepository;
        this.flightBookingConverter = flightBookingConverter;
    }

    @Transactional
    public FlightBookingResponseDto bookFlight(String userId, FlightBookingRequestDto flightBookingRequestDto) {
        return flightBookingRequestDto.getFlightBookingType().bookFlight(this, getUser(userId), flightBookingRequestDto);
    }

    public FlightBookingResponseDto bookOnewayFlight(User user, FlightBookingRequestDto flightBookingRequestDto) {
        List<Flight> availableFlights = flightBookingRepository.findAvailableFlights(
                flightBookingRequestDto.getDepartureFlight().getDepartureCity(),
                flightBookingRequestDto.getDepartureFlight().getDepartureDateTime(),
                flightBookingRequestDto.getDepartureFlight().getArrivalCity(),
                flightBookingRequestDto.getDepartureFlight().getArrivalDateTime(),
                flightBookingRequestDto.getDepartureFlight().getCabinClass(),
                flightBookingRequestDto.getDepartureFlight().getAirplaneId());

        List<Flight> selectedFlights = getRandomFlights(availableFlights, flightBookingRequestDto.getDepartureFlight().getHeadCount());
        bookSelectedFlights(user, selectedFlights);

        long totalPoints = calculateTotalPoints(selectedFlights);
        payPoints(user, totalPoints);

        return flightBookingConverter.convertFlightBookingResponseDtoByOneway(selectedFlights);
    }

    public FlightBookingResponseDto bookRoundFlight(User user, FlightBookingRequestDto flightBookingRequestDto) {
        FlightBookingResponseDto flightBookingResponseDto = bookOnewayFlight(user, flightBookingRequestDto);

        List<Flight> availableFlights = flightBookingRepository.findAvailableFlights(
                flightBookingRequestDto.getArrivalFlight().getDepartureCity(),
                flightBookingRequestDto.getArrivalFlight().getDepartureDateTime(),
                flightBookingRequestDto.getArrivalFlight().getArrivalCity(),
                flightBookingRequestDto.getArrivalFlight().getArrivalDateTime(),
                flightBookingRequestDto.getArrivalFlight().getCabinClass(),
                flightBookingRequestDto.getArrivalFlight().getAirplaneId());

        List<Flight> selectedFlights = getRandomFlights(availableFlights, flightBookingRequestDto.getArrivalFlight().getHeadCount());
        bookSelectedFlights(user, selectedFlights);

        long totalPoints = calculateTotalPoints(selectedFlights);
        payPoints(user, totalPoints);

        return flightBookingConverter.convertFlightBookingResponseDtoByRound(flightBookingResponseDto, selectedFlights);

    }

    @Transactional(readOnly = true)
    public BookedFlightResponseDto getBookedFlight(Long bookingId) {
        Optional<Flight> bookedFlight = flightBookingRepository.findById(bookingId);

        return flightBookingConverter.convertBookedFlightResponseDto(bookedFlight.get());
    }

    @Transactional(readOnly = true)
    public List<BookedFlightResponseDto> getBookedFlights(String userId) {
        User user = getUser(userId);
        List<Flight> bookedFlights = flightBookingRepository.findAllByUser(user);

        return flightBookingConverter.convertBookedFlightResponseDtos(bookedFlights);
    }

    @Transactional
    public CancelBookedFlightDto cancelBookedFlight(String userId, Long bookingId) {
        User user = getUser(userId);
        Optional<Flight> canceledFlight = flightBookingRepository.findById(bookingId);

        if (!isValidCancel(canceledFlight.get())) {
            // 예외처리
        }
        cancelFlight(user, canceledFlight.get());

        return flightBookingConverter.convertCancelBookedFlightDto(bookingId);
    }

    private long calculateTotalPoints(List<Flight> selectedFlights) {
        return selectedFlights.stream().mapToLong(Flight::getCost).sum();
    }

    private List<Flight> getRandomFlights(List<Flight> availableFlights, int headCount) {
        Collections.shuffle(availableFlights);
        return IntStream.range(0, headCount).mapToObj(availableFlights::get).collect(Collectors.toList());
    }

    private void bookSelectedFlights(User user, List<Flight> selectedFlights) {
        for (Flight selectedFlight : selectedFlights) {
            bookSelectedFlight(selectedFlight, user);
        }
    }

    private void bookSelectedFlight(Flight selectedFlight, User user) {
        selectedFlight.bookFlightByUserId(user);
    }

    private void payPoints(User user, long totalPrice) {
        user.subtractPoint(totalPrice);
    }

    private void refundPoints(User user, long refundPoints) {
        user.addPoint(refundPoints);
    }

    private void cancelFlight(User user, Flight flight) {
        flightBookingRepository.delete(flight);
        refundPoints(user, flight.getCost());

    }

    private boolean isValidCancel(Flight canceledFlight) {
        return canceledFlight.getAirplaneSeat().getAirplane().getAirline().isCancellationAllowed();
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));
    }

}
