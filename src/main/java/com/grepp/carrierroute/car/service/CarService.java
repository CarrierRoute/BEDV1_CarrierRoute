package com.grepp.carrierroute.car.service;

import com.grepp.carrierroute.booking.repository.CarBookingRepository;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.car.domain.CarCompany;
import com.grepp.carrierroute.car.dto.CarCreationDto;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import com.grepp.carrierroute.car.dto.CarSearchDto;
import com.grepp.carrierroute.exception.NotFoundException;
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
        List<Long> bookedCarIds = bookingCarRepository.findBookedCarIdsByDateTime(carSearchDto.getStartDateTime(), carSearchDto.getEndDateTime());

        return carSearchDto.getSearchType().get(carRepository, bookedCarIds, carSearchDto)
                .stream()
                .map(carConverter::convertCarResponseDto)
                .collect(Collectors.toList());
    }

    public CarResponseDto saveCar(CarCreationDto carCreationDto, MultipartFile multipartFile) {
        UploadFile uploadFile = fileStore.storeFile(multipartFile);
        CarCompany carCompany = getCarCompany(carCreationDto);
        Car car = createCar(carCreationDto, uploadFile, carCompany);

        return carConverter.convertCarResponseDto(car);
    }

    private Car createCar(CarCreationDto carCreationDto, UploadFile uploadFile, CarCompany carCompany) {
        return Car.builder()
                .uploadFile(uploadFile)
                .grade(carCreationDto.getGrade())
                .licencePlate(carCreationDto.getLicencePlate())
                .price(carCreationDto.getPrice())
                .maxPassengers(carCreationDto.getMaxPassengers())
                .carCompany(carCompany)
                .build();
    }

    private CarCompany getCarCompany(CarCreationDto carCreationDto) {
        return carCompanyRepository.findById(carCreationDto.getCarCompanyId())
                .orElseThrow(() -> new NotFoundException(CarCompany.class, carCreationDto.getCarCompanyId()));
    }
}
