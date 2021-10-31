package com.grepp.carrierroute.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CarBookingRequestDto {

    @NotBlank
    private String carId;

    @NotEmpty
    private LocalDateTime startDateTime;

    @NotEmpty
    private LocalDateTime endDateTime;
}
