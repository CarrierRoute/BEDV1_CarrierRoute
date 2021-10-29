package com.grepp.carrierroute.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CarBookingRequestDto {

    @NotBlank
    private String carId;

    @NotBlank
    private LocalDateTime startDateTime;

    @NotBlank
    private LocalDateTime endDateTime;
}
