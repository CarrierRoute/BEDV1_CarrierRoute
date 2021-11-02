package com.grepp.carrierroute.hotel.service.converter;

import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.domain.RoomType;
import com.grepp.carrierroute.hotel.dto.HotelRoomDto;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class HotelConverter {
    public HotelSearchResponseDto convertToHotelSearchResponseDto(Hotel hotel, List<HotelRoom> rooms){
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
                .roomsByType(convertHotelRoomDtosByType(rooms))
                .build();
    }

    private HotelRoomDto convertToHotelRoomDto(HotelRoom hotelRoom){
        return HotelRoomDto.builder()
                .roomId(hotelRoom.getId())
                .roomType(hotelRoom.getRoomType())
                .maxNumOfGuest(hotelRoom.getMaxNumOfGuest())
                .pricePerDay(hotelRoom.getPricePerDay())
                .roomPhotoUrl(hotelRoom.getPhotoUrl())
                .build();
    }

    private Map<RoomType, List<HotelRoomDto>> convertHotelRoomDtosByType(List<HotelRoom> hotelRooms){
        return hotelRooms.stream()
                .map(this::convertToHotelRoomDto)
                .collect(Collectors.groupingBy(HotelRoomDto::getRoomType));
    }
}
