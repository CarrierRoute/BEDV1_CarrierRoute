package com.grepp.carrierroute.hotel.service.converter;

import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import org.springframework.stereotype.Component;

@Component
public class HotelConverter {
    public HotelSearchResponseDto convertHotelSearchResponseDto(HotelRoom hotelRoom){
        return HotelSearchResponseDto.builder()
                .roomId(hotelRoom.getId())
                .hotelId(hotelRoom.getHotel().getId())
                .roomType(hotelRoom.getRoomType())
                .roomCount(hotelRoom.getCount())
                .maxGuestNumber(hotelRoom.getMaxGuestNumber())
                .pricePerDay(hotelRoom.getPricePerDay())
                .roomPhotoUrl(hotelRoom.getPhotoUrl())
                .hotelName(hotelRoom.getHotel().getName())
                .address(hotelRoom.getHotel().getAddress())
                .city(hotelRoom.getHotel().getCity())
                .country(hotelRoom.getHotel().getCountry())
                .guestRating(hotelRoom.getHotel().getGuestRating())
                .starRating(hotelRoom.getHotel().getStarRating())
                .hotelDescription(hotelRoom.getHotel().getDescription())
                .checkInTime(hotelRoom.getHotel().getCheckInTime())
                .checkOutTime(hotelRoom.getHotel().getCheckOutTime())
                .hotelPhotoUrl(hotelRoom.getHotel().getPhotoUrl())
                .build();
    }
}
