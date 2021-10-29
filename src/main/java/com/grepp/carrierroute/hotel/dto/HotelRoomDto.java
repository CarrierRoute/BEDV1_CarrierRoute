package com.grepp.carrierroute.hotel.dto;

import com.grepp.carrierroute.hotel.domain.RoomType;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HotelRoomDto {
    private Long roomId;
    private RoomType roomType;
    private int roomCount;
    private int maxGuestNumber;
    private long pricePerDay;
    private String roomPhotoUrl;
}
