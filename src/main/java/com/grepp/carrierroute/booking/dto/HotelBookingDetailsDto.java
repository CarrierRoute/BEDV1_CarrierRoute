package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.hotel.domain.RoomType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Builder
public class HotelBookingDetailsDto {
    private Long bookingId;
    private Long hotelRoomId;
    private Long hotelId;
    private String hotelName;
    private String hotelAddress;
    private RoomType roomType;
    private int guestNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private long price;
    private String additionalRequest;
    private LocalDateTime bookingDate;
}
