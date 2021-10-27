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
    private int roomCount;
    private int maxGuestNumber;
    private long pricePerDay;
    private String roomPhotoUrl;
    private String hotelName;
    private String address;
    private String city;
    private String country;
    private float guestRating;
    private int starRating;
    private String hotelDescription;
    private LocalTime checkInTime;
    private LocalTime checkOutTime;
    private String hotelPhotoUrl;
}
