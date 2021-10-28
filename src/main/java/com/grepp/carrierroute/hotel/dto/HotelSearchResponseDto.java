package com.grepp.carrierroute.hotel.dto;

import com.grepp.carrierroute.hotel.domain.RoomType;
import lombok.Builder;
import lombok.Getter;
import java.time.LocalTime;

@Getter
@Builder
public class HotelSearchResponseDto {
    private Long roomId;
    private Long hotelId;
    private RoomType roomType;
    private Integer roomCount;
    private Integer maxGuestNumber;
    private Long pricePerDay;
    private String roomPhotoUrl;
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
}
