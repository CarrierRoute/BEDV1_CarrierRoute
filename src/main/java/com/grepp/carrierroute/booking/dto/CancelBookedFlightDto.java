package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CancelBookedFlightDto {
    private Long bookingId;
}
