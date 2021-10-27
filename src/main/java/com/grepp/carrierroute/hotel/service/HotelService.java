package com.grepp.carrierroute.hotel.service;

import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.dto.DestinationType;
import com.grepp.carrierroute.hotel.dto.HotelSearchRequestDto;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import com.grepp.carrierroute.hotel.exception.HotelInfoNotFoundedException;
import com.grepp.carrierroute.hotel.repository.HotelRepository;
import com.grepp.carrierroute.hotel.repository.HotelRoomRepository;
import com.grepp.carrierroute.hotel.service.converter.HotelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class HotelService {
    private final HotelRoomRepository hotelRoomRepository;
    private final HotelRepository hotelRepository;
    private final HotelConverter converter;

    public HotelSearchResponseDto findRoomBy(Long id) throws HotelInfoNotFoundedException {
        return hotelRoomRepository.findById(id)
                .map(converter::convertHotelSearchResponseDto)
                .orElseThrow(()->new HotelInfoNotFoundedException(ErrorMessage.HOTEL_ROOM_NOT_FOUNDED));
    }

    public List<HotelSearchResponseDto> findRoomsBy(HotelSearchRequestDto requestDto){
        List<HotelRoom> hotelRooms = findRoomsBy(requestDto.getDestinationType(), requestDto.getDestinationName());
        hotelRooms = filterRoomsBy(hotelRooms, requestDto.getGuestNumber(), requestDto.getNumOfRoom());

        return hotelRooms.stream()
                .map(converter::convertHotelSearchResponseDto)
                .collect(Collectors.toList());
    }

    private List<HotelRoom> findRoomsBy(DestinationType type, String destination){
        List<Hotel> hotels = findHotelsBy(type, destination);
        List<HotelRoom> rooms = new ArrayList<>();

        hotels.forEach(hotel -> rooms.addAll(hotel.getHotelRooms()));

        return rooms;
    }

    private List<Hotel> findHotelsBy(DestinationType type, String destination){
        List<Hotel> hotels = new ArrayList<>();

        switch (type){
            case CITY:
                hotels = hotelRepository.findByCity(destination);
                break;
            case COUNTRY:
                hotels = hotelRepository.findByCountry(destination);
                break;
            case HOTEL:
                hotels = hotelRepository.findByName(destination);
                break;
        }

        return hotels;
    }

    private List<HotelRoom> filterRoomsBy(List<HotelRoom> rooms, int guestNumber, int numOfRoom){
        return rooms.stream()
                    .filter(room -> room.getMaxGuestNumber() >= guestNumber)
                    .filter(room -> room.getCount() >= numOfRoom)
                    .collect(Collectors.toList());
    }
}
