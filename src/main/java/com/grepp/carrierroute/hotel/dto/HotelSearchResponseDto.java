package com.grepp.carrierroute.hotel.dto;

import com.grepp.carrierroute.hotel.domain.RoomType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;

@Getter
@Builder
public class HotelSearchResponseDto {
    private long hotelId;
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
    private boolean isCancellationAllowed;
    private Map<RoomType, List<HotelRoomDetailsDto>> roomsByType;
}
