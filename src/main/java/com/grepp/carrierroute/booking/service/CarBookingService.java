package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.CarBooking;
import com.grepp.carrierroute.booking.exception.AlreadyBookedException;
import com.grepp.carrierroute.booking.exception.CarBookingNotFoundException;
import com.grepp.carrierroute.booking.exception.UserAndCarBookingNotMatchException;
import com.grepp.carrierroute.booking.service.converter.CarBookingConverter;
import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.exception.CarNotFoundException;
import com.grepp.carrierroute.car.repository.CarRepository;
import com.grepp.carrierroute.user.domain.User;
import com.grepp.carrierroute.user.exception.UserNotFoundException;
import com.grepp.carrierroute.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarBookingService {

    private final UserRepository userRepository;
    private final CarBookingRepository carBookingRepository;
    private final CarRepository carRepository;
    private final CarBookingConverter carBookingConverter;

    @Transactional
    public CarBookingResponseDto bookCar(CarBookingRequestDto carBookingRequestDto, String userId) {
        User user = getUser(userId);
        Car car = getCar(carBookingRequestDto);

        validateBookingState(carBookingRequestDto);

        pay(carBookingRequestDto, user, car);

        CarBooking carBooking = createCarBooking(carBookingRequestDto, user, car);

        CarBooking save = carBookingRepository.save(carBooking);

        return carBookingConverter.convertCarBookingResponseDto(save, car);
    }

    private CarBooking createCarBooking(CarBookingRequestDto carBookingRequestDto, User user, Car car) {
        return CarBooking.builder()
                .car(car)
                .user(user)
                .startDateTime(carBookingRequestDto.getStartDateTime())
                .endDateTime(carBookingRequestDto.getEndDateTime())
                .build();
    }

    private void pay(CarBookingRequestDto carBookingRequestDto, User user, Car car) {
        long totalPrice = car.getPrice() * getBookingDuration(carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime());
        user.subtractPoint(totalPrice);
    }

    private void validateBookingState(CarBookingRequestDto carBookingRequestDto) {
        carBookingRepository.findByIdAndDateTime(carBookingRequestDto.getCarId(), carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime())
                .ifPresent(carBooking -> {
                    throw new AlreadyBookedException(MessageFormat.format("Already Booked Car. Car Id : {0}", carBookingRequestDto.getCarId()));
                });
    }

    private long getBookingDuration(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return ChronoUnit.HOURS.between(startDateTime, endDateTime);
    }

    private Car getCar(CarBookingRequestDto carBookingRequestDto) {
        return carRepository.findById(carBookingRequestDto.getCarId())
                .orElseThrow(() -> new CarNotFoundException(MessageFormat.format("Car Not Found, Car Id : {0}", carBookingRequestDto.getCarId())));
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(MessageFormat.format("User Not Found. User Id : {0}", userId)));
    }

    @Transactional(readOnly = true)
    public List<CarBookingResponseDto> getCarBookings(String userId) {
        return carBookingRepository.findCarBookings(userId);
    }

    @Transactional(readOnly = true)
    public CarBookingResponseDto getCarBooking(Long bookingId, String userId) {
        CarBooking carBooking = getCarBooking(bookingId);

        String id = carBooking.getCar().getId();
        return carRepository.findById(id)
                .map(car -> carBookingConverter.convertCarBookingResponseDto(carBooking, car))
                .orElseThrow(() -> new RuntimeException("Not Found Car. Car Id : " + id));
    }

    private CarBooking getCarBooking(Long bookingId) {
        return carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new CarBookingNotFoundException(MessageFormat.format("Not Found CarBooking. Booking Id :{0}", bookingId)));
    }

    @Transactional
    public void cancelBooking(Long bookingId, String userId) {
        CarBooking carBooking = getCarBooking(bookingId);

        User user = getUser(userId);

        refund(carBooking, user);

        carBookingRepository.delete(carBooking);
    }

    private void refund(CarBooking carBooking, User user) {
        long totalPrice = carBooking.getCar().getPrice() * getBookingDuration(carBooking.getStartDateTime(), carBooking.getEndDateTime());
        user.addPoint(totalPrice);
    }
}
