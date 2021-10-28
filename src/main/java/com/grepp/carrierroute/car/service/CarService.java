package com.grepp.carrierroute.car.service;

import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import com.grepp.carrierroute.car.dto.CarSearchDto;
import com.grepp.carrierroute.car.repository.CarRepository;
import com.grepp.carrierroute.car.service.converter.CarConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CarService {

    private final CarRepository carRepository;
    private final CarBookingRepository bookingCarRepository;
    private final CarConverter carConverter;

    public List<CarResponseDto> findAllByCondition(CarSearchDto carSearchDto) {
        List<String> bookedCarIds = bookingCarRepository.findBookedCarIdsByDate(carSearchDto.getStartDateTime(), carSearchDto.getEndDateTime());

        return carRepository.findByPlaceAmongNotBookedCars(carSearchDto.getPlace(), bookedCarIds)
                .stream()
                .map(carConverter::convertCarResponseDto)
                .collect(Collectors.toList());
    }
}
