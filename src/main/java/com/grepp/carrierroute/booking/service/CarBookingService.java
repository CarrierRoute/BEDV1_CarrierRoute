package com.grepp.carrierroute.booking.service;

import com.grepp.carrierroute.booking.domain.CarBooking;
import com.grepp.carrierroute.booking.service.converter.CarBookingConverter;
import com.grepp.carrierroute.booking.dto.CarBookingRequestDto;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarBookingService {

    private final CarBookingRepository carBookingRepository;
    private final CarRepository carRepository;
    private final CarBookingConverter carBookingConverter;

    @Transactional
    public CarBookingResponseDto bookCar(CarBookingRequestDto carBookingRequestDto) {
        Car car = carRepository.findById(carBookingRequestDto.getCarId())
                .orElseThrow(() -> new RuntimeException("Car Not Found, Car id : " + carBookingRequestDto.getCarId()));

        carBookingRepository.findByIdAndDateTime(carBookingRequestDto.getCarId(), carBookingRequestDto.getStartDateTime(), carBookingRequestDto.getEndDateTime())
                .ifPresent(carBooking -> {
                    throw new RuntimeException("Already Booked Car. Car Id : " + carBookingRequestDto.getCarId());
                });

        CarBooking carBooking = CarBooking.builder()
                .car(car)
                .startDateTime(carBookingRequestDto.getStartDateTime())
                .endDateTime(carBookingRequestDto.getEndDateTime())
                .build();

        CarBooking save = carBookingRepository.save(carBooking);

        return carBookingConverter.convertCarBookingResponseDto(save, car);
    }

    @Transactional(readOnly = true)
    public List<CarBookingResponseDto> getCarBookings() {
        return carBookingRepository.findCarBookings();
    }

    @Transactional(readOnly = true)
    public CarBookingResponseDto getCarBooking(Long bookingId) {
        CarBooking carBooking = carBookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Not Found CarBooking. Booking Id :" + bookingId));

        String id = carBooking.getCar().getId();
        Car car = carRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Not Found Car. Car Id : " + id));

        return carBookingConverter.convertCarBookingResponseDto(carBooking, car);
    }

    @Transactional
    public void cancelBooking(Long bookingId) {
        carBookingRepository.deleteById(bookingId);
    }
}
