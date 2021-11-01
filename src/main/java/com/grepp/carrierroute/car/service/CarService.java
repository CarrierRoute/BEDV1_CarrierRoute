package com.grepp.carrierroute.car.service;

import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.domain.CarCompany;
import com.grepp.carrierroute.car.domain.CarGrade;
import com.grepp.carrierroute.car.dto.CarCreationDto;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import com.grepp.carrierroute.car.dto.CarSearchDto;
import com.grepp.carrierroute.car.dto.CarSearchType;
import com.grepp.carrierroute.car.exception.CarCompanyNotFoundException;
import com.grepp.carrierroute.car.repository.CarCompanyRepository;
import com.grepp.carrierroute.car.repository.CarRepository;
import com.grepp.carrierroute.car.service.converter.CarConverter;
import com.grepp.carrierroute.common.file.FileStore;
import com.grepp.carrierroute.common.file.UploadFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final CarCompanyRepository carCompanyRepository;
    private final CarBookingRepository bookingCarRepository;
    private final CarConverter carConverter;
    private final FileStore fileStore;

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

    public CarResponseDto saveCar(CarCreationDto carCreationDto, MultipartFile multipartFile) {
        UploadFile uploadFile = fileStore.storeFile(multipartFile);
        CarCompany carCompany = getCarCompany(carCreationDto);
        Car car = createCar(uploadFile, carCompany);

        return carConverter.convertCarResponseDto(car);
    }

    private Car createCar(UploadFile uploadFile, CarCompany carCompany) {
        return Car.builder()
                .uploadFile(uploadFile)
                .grade(CarGrade.MINI)
                .licencePlate("12가 4567")
                .price(10000)
                .maxPassengers(4)
                .carCompany(carCompany)
                .build();
    }

    private CarCompany getCarCompany(CarCreationDto carCreationDto) {
        return carCompanyRepository.findById(carCreationDto.getCarCompanyId())
                .orElseThrow(() -> new CarCompanyNotFoundException(" "));
    }
}
