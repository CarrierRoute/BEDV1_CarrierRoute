package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.CarBooking;
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

        carBookingRepository.findByCarIdAndDateTime(carBookingRequestDto.getCarId(), carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime())
                .ifPresent(carBooking -> {
                    throw new RuntimeException("Already Booked Car. Car Id : " + carBookingRequestDto.getCarId());
                });

        CarBooking carBooking = CarBooking.builder()
                .car(car)
                .user(user)
                .startDateTime(carBookingRequestDto.getStartDateTime())
                .endDateTime(carBookingRequestDto.getEndDateTime())
                .build();

        CarBooking save = carBookingRepository.save(carBooking);

        return carBookingConverter.convertCarBookingResponseDto(save, car);
    }

    private Car getCar(CarBookingRequestDto carBookingRequestDto) {
        return carRepository.findById(carBookingRequestDto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car Not Found, Car Id : " + carBookingRequestDto.getCarId()));
    }

    private User getUser(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User Not Found. User Id : " + userId));
    }

    @Transactional(readOnly = true)
    public List<CarBookingResponseDto> getCarBookings(String userId) {
        return carBookingRepository.findCarBookings(userId);
    }

    @Transactional(readOnly = true)
    public CarBookingResponseDto getCarBooking(Long bookingId, String userId) {
        CarBooking carBooking = carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Not Found CarBooking. Booking Id :" + bookingId));

        validUserRelatedToBooking(bookingId, userId, carBooking);

        String id = carBooking.getCar().getId();
        return carRepository.findById(id)
                .map(car -> carBookingConverter.convertCarBookingResponseDto(carBooking, car))
                .orElseThrow(() -> new RuntimeException("Not Found Car. Car Id : " + id));
    }

    private void validUserRelatedToBooking(Long bookingId, String userId, CarBooking carBooking) {
        if (!carBooking.getUser().getId().equals(userId)) {
            throw new RuntimeException("UserId related to bookingID is not correct. User Id : " + userId + ", Booking ID : " + bookingId);
        }
    }

    @Transactional
    public void cancelBooking(Long bookingId, String userId) {
        CarBooking carBooking = carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Not Found Carbooking. Booking Id : " + bookingId));

        validUserRelatedToBooking(bookingId, userId, carBooking);

        carBookingRepository.delete(carBooking);
    }
}
