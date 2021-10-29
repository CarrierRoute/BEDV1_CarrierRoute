package com.grepp.carrierroute.hotel.service;

import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.dto.*;
import com.grepp.carrierroute.hotel.exception.EmptyHotelInfoException;
import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import com.grepp.carrierroute.hotel.exception.HotelInfoNotFoundedException;
import com.grepp.carrierroute.hotel.repository.HotelRepository;
import com.grepp.carrierroute.hotel.service.converter.HotelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelConverter converter;

    public HotelSearchResponseDto findHotelBy(Long id, HotelSearchRequestDto requestDto){
        Hotel hotel = findHotelBy(id);
        List<HotelRoomDto> roomsMatchedByRequest = getRoomsBy(hotel, requestDto);

        if(roomsMatchedByRequest.isEmpty()){
            throw new EmptyHotelInfoException(ErrorMessage.HOTEL_NOT_FOUNDED_MATCHED_BY_REQUEST);
        }

        return converter.convertHotelSearchResponseDto(hotel, roomsMatchedByRequest);
    }

    public List<HotelSearchResponseDto> findHotelsBy(HotelSearchRequestDto requestDto){
        List<HotelSearchResponseDto> hotelSearchResults = searchHotelsBy(requestDto);

        if(hotelSearchResults.isEmpty()){
            throw new EmptyHotelInfoException(ErrorMessage.HOTEL_NOT_FOUNDED_MATCHED_BY_REQUEST);
        }

        return hotelSearchResults;
    }

    private Hotel findHotelBy(Long id) throws HotelInfoNotFoundedException{
        return hotelRepository.findById(id)
                .orElseThrow(()->new HotelInfoNotFoundedException(ErrorMessage.HOTEL_NOT_FOUNDED));
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

    private List<HotelRoomDto> getRoomsBy(Hotel hotel, HotelSearchRequestDto requestDto){
        return hotel.getHotelRooms()
                .stream()
                .filter(room -> room.isAvailable(requestDto.getGuestNumber(), requestDto.getNumOfRoom()))
                .map(converter::convertHotelRoomDto)
                .collect(Collectors.toList());
    }

    private List<HotelSearchResponseDto> searchHotelsBy(HotelSearchRequestDto requestDto){
        List<HotelSearchResponseDto> hotelSearchResults = new ArrayList<>();
        List<Hotel> hotels = findHotelsBy(requestDto.getDestinationType(), requestDto.getDestinationName());

        hotels.forEach(hotel -> {
            List<HotelRoomDto> roomsMatchedByRequest = getRoomsBy(hotel, requestDto);
            if(!roomsMatchedByRequest.isEmpty()) {
                hotelSearchResults.add(converter.convertHotelSearchResponseDto(hotel, roomsMatchedByRequest));
            }
        });

        return hotelSearchResults;
    }
}
