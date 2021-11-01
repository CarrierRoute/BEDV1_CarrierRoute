package com.grepp.carrierroute.hotel.service;

import com.grepp.carrierroute.booking.repository.HotelBookingRepository;
import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.domain.RoomType;
import com.grepp.carrierroute.hotel.dto.HotelSearchRequestDto;
import com.grepp.carrierroute.hotel.repository.HotelRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelRoomService {
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelRoomRepository hotelRoomRepository;

    public List<HotelRoom> findRoomsBy(Hotel hotel, HotelSearchRequestDto requestDto){
        Map<RoomType, List<HotelRoom>> availableRoomsByType = hotel.getHotelRooms()
                .stream()
                .filter(room -> !hotelBookingRepository.existsByHotelRoomAndDate(room.getId(), requestDto.getCheckInDate(), requestDto.getCheckOutDate()))
                .collect(Collectors.groupingBy(HotelRoom::getRoomType));

        return availableRoomsByType.keySet()
                .stream()
                .filter(roomType -> availableRoomsByType.get(roomType).size() >= requestDto.getNumOfRoom())
                .map(availableRoomsByType::get)
                .flatMap(List::stream)
                .filter(room -> room.getMaxNumOfGuest() >= requestDto.getNumOfGuest())
                .collect(Collectors.toList());
    }
}
