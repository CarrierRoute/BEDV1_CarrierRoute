package com.grepp.carrierroute.car.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CarSearchDto {

    private String searchName;
    private CarSearchType searchType;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private Integer age;
}
