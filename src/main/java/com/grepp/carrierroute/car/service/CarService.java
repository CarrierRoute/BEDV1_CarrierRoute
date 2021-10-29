package com.grepp.carrierroute.car.service;

import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import com.grepp.carrierroute.car.dto.CarSearchDto;
import com.grepp.carrierroute.car.dto.CarSearchType;
import com.grepp.carrierroute.car.repository.CarRepository;
import com.grepp.carrierroute.car.service.converter.CarConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarBookingRepository bookingCarRepository;
    private final CarConverter carConverter;

    @Transactional(readOnly = true)
    public List<CarResponseDto> findAllByCondition(CarSearchDto carSearchDto) {
        List<String> bookedCarIds = bookingCarRepository.findBookedCarIdsByDateTime(carSearchDto.getStartDateTime(), carSearchDto.getEndDateTime());

        if (carSearchDto.getSearchType() == CarSearchType.AIRPORT) {
            return carRepository.findByAirPortAmongNotBookedCars(carSearchDto.getSearchName(), bookedCarIds)
                    .stream()
                    .map(carConverter::convertCarResponseDto)
                    .collect(Collectors.toList());
        }

        if (carSearchDto.getSearchType() == CarSearchType.CITY) {
            return carRepository.findByCityAmongNotBookedCars(carSearchDto.getSearchName(), bookedCarIds)
                    .stream()
                    .map(carConverter::convertCarResponseDto)
                    .collect(Collectors.toList());
        }

        throw new RuntimeException("SearchType is wrong");
    }
}
