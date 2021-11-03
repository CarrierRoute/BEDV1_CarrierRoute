package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.CarBooking;
import com.grepp.carrierroute.common.RefundPolicy;
import com.grepp.carrierroute.exception.NotFoundException;
import com.grepp.carrierroute.exception.booking.AlreadyBookedCarException;
import com.grepp.carrierroute.booking.service.converter.CarBookingConverter;
import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.repository.CarRepository;
import com.grepp.carrierroute.user.domain.User;
import com.grepp.carrierroute.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        long totalPrice = car.calculatePrice(carBookingRequestDto.getAge(), carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime());

        pay(user, totalPrice);

        CarBooking carBooking = createCarBooking(carBookingRequestDto, user, car);

        CarBooking save = carBookingRepository.save(carBooking);

        return carBookingConverter.convertCarBookingResponseDto(save, car);
    }

    private void pay(User user, long totalPrice) {
        user.subtractPoint(totalPrice);
    }

    private CarBooking createCarBooking(CarBookingRequestDto carBookingRequestDto, User user, Car car) {
        return CarBooking.builder()
                .car(car)
                .user(user)
                .startDateTime(carBookingRequestDto.getStartDateTime())
                .endDateTime(carBookingRequestDto.getEndDateTime())
                .build();
    }

    private void validateBookingState(CarBookingRequestDto carBookingRequestDto) {
        carBookingRepository.findByIdAndDateTime(carBookingRequestDto.getCarId(), carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime())
                .ifPresent(carBooking -> {
                    throw new AlreadyBookedCarException(carBookingRequestDto.getCarId());
                });
    }

    private Car getCar(CarBookingRequestDto carBookingRequestDto) {
        return carRepository.findById(carBookingRequestDto.getCarId())
                .orElseThrow(() -> new NotFoundException(Car.class, carBookingRequestDto.getCarId()));
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException(User.class, userId));
    }

    @Transactional(readOnly = true)
    public List<CarBookingResponseDto> getCarBookings(String userId) {
        return carBookingRepository.findCarBookings(userId);
    }

    @Transactional(readOnly = true)
    public CarBookingResponseDto getCarBookingDetail(Long bookingId) {
        CarBooking carBooking = getCarBooking(bookingId);

        Long id = carBooking.getCar().getId();
        return carRepository.findById(id)
                .map(car -> carBookingConverter.convertCarBookingResponseDto(carBooking, car))
                .orElseThrow(() -> new NotFoundException(Car.class, id));
    }

    private CarBooking getCarBooking(Long bookingId) {
        return carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new NotFoundException(CarBooking.class, bookingId));
    }

    @Transactional
    public void cancelBooking(Long bookingId, String userId) {
        CarBooking carBooking = getCarBooking(bookingId);

        User user = getUser(userId);

        refund(carBooking, user);

        carBookingRepository.delete(carBooking);
    }

    private void refund(CarBooking carBooking, User user) {
        long price = carBooking.getPrice();
        LocalDateTime startDateTime = carBooking.getStartDateTime();
        RefundPolicy refundPolicy = carBooking.getCar().getCarCompany().getRefundPolicy();

        long remainingDays = getRemainingDays(startDateTime);

        long refundPrice = refundPolicy.calculateRefundPrice(price, remainingDays);

        refund(user, refundPrice);
    }

    private void refund(User user, long refundPrice) {
        user.addPoint(refundPrice);
    }

    private long getRemainingDays(LocalDateTime startDateTime) {
        return ChronoUnit.DAYS.between(startDateTime, LocalDateTime.now());
    }
}
