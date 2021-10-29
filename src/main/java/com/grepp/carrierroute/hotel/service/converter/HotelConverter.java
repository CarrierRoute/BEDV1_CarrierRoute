package com.grepp.carrierroute.hotel.service.converter;

import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.dto.HotelRoomDto;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelConverter {
    public HotelSearchResponseDto convertHotelSearchResponseDto(Hotel hotel, List<HotelRoomDto> roomDtos){
        return HotelSearchResponseDto.builder()
                .hotelId(hotel.getId())
                .hotelName(hotel.getName())
                .address(hotel.getAddress())
                .city(hotel.getCity())
                .country(hotel.getCountry())
                .guestRating(hotel.getGuestRating())
                .starRating(hotel.getStarRating())
                .hotelDescription(hotel.getDescription())
                .checkInTime(hotel.getCheckInTime())
                .checkOutTime(hotel.getCheckOutTime())
                .hotelPhotoUrl(hotel.getPhotoUrl())
                .rooms(roomDtos)
                .build();
    }

    public HotelRoomDto convertHotelRoomDto(HotelRoom hotelRoom){
        return HotelRoomDto.builder()
                .roomId(hotelRoom.getId())
                .roomType(hotelRoom.getRoomType())
                .roomCount(hotelRoom.getCount())
                .maxGuestNumber(hotelRoom.getMaxGuestNumber())
                .pricePerDay(hotelRoom.getPricePerDay())
                .roomPhotoUrl(hotelRoom.getPhotoUrl())
                .build();
    }
}
