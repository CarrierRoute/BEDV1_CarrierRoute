package com.grepp.carrierroute.car.controller;

import com.grepp.carrierroute.car.dto.CarCreationDto;
import com.grepp.carrierroute.car.dto.CarResponseDto;
import com.grepp.carrierroute.car.dto.CarSearchDto;
import com.grepp.carrierroute.car.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarResponseDto>> findAllByCondition(@Valid @RequestBody CarSearchDto carSearchDto) {
        return new ResponseEntity<>(carService.findAllByCondition(carSearchDto), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CarResponseDto> addCar(@RequestBody CarCreationDto carCreationDto, @RequestParam MultipartFile multipartFile) {
        return new ResponseEntity<>(carService.saveCar(carCreationDto, multipartFile), HttpStatus.OK);
    }
}
