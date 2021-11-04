package com.grepp.carrierroute.booking.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
public class CarBookingRequestDto {

    @NotEmpty
    private Long carId;

    @NotEmpty
    private LocalDateTime startDateTime;

    @NotEmpty
    private LocalDateTime endDateTime;

    @Range(min = 18, max = 100)
    private int age;
}
