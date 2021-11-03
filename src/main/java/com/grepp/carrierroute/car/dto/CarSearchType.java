package com.grepp.carrierroute.car.dto;

import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.repository.CarRepository;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

@Getter
public enum CarSearchType {

    AIRPORT("Airport") {
        public List<Car> get(CarRepository carRepository, List<Long> bookedCarIds, CarSearchDto carSearchDto){
            return carRepository.findByAirPortAmongNotBookedCars(carSearchDto.getSearchName(), bookedCarIds);
        }
    },
    CITY("City"){
        public List<Car> get(CarRepository carRepository, List<Long> bookedCarIds, CarSearchDto carSearchDto){
            return carRepository.findByCityAmongNotBookedCars(carSearchDto.getSearchName(), bookedCarIds);
        }
    };

    private final String value;

    CarSearchType(String value) {
        this.value = value;
    }

    public abstract List<Car> get(CarRepository carRepository, List<Long> bookedCarIds, CarSearchDto carSearchDto);
}
