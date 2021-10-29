package com.grepp.carrierroute.hotel.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Builder
public class HotelSearchResponseDto {
    private Long hotelId;
    private String hotelName;
    private String address;
    private String city;
    private String country;
    private Float guestRating;
    private Integer starRating;
    private String hotelDescription;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String hotelPhotoUrl;
    private List<HotelRoomDto> rooms;
}
