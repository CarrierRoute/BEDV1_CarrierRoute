package com.grepp.carrierroute.booking.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
public class HotelBookingResponseDto {
    LocalDate checkInDate;
    LocalDate checkOutDate;
    long totalPrice;
    int numberOfGuestPerRoom;
    List<Long> bookingIdList;
}
