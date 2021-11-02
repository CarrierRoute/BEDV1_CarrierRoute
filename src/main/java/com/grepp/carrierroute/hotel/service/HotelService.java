package com.grepp.carrierroute.hotel.service;

import com.grepp.carrierroute.booking.repository.HotelBookingRepository;
import com.grepp.carrierroute.hotel.domain.Hotel;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.domain.RoomType;
import com.grepp.carrierroute.hotel.dto.*;
import com.grepp.carrierroute.hotel.exception.EmptyHotelInfoException;
import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import com.grepp.carrierroute.hotel.exception.HotelInfoNotFoundedException;
import com.grepp.carrierroute.hotel.repository.HotelRepository;
import com.grepp.carrierroute.hotel.service.converter.HotelConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class HotelService {
    private final HotelRepository hotelRepository;
    private final HotelBookingRepository hotelBookingRepository;
    private final HotelConverter converter;

    public HotelSearchResponseDto findHotelBy(Long id, HotelSearchRequestDto requestDto){
        Hotel hotel = findHotelBy(id);
        List<HotelRoom> roomsMatchedByRequest = findRoomsBy(hotel, requestDto);

        if(roomsMatchedByRequest.isEmpty()){
            throw new EmptyHotelInfoException(ErrorMessage.HOTEL_NOT_FOUNDED_MATCHED_BY_REQUEST);
        }

        return converter.convertToHotelSearchResponseDto(hotel, roomsMatchedByRequest);
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

    private List<HotelSearchResponseDto> searchHotelsBy(HotelSearchRequestDto requestDto){
        List<HotelSearchResponseDto> hotelSearchResults = new ArrayList<>();

        findHotelsBy(requestDto.getDestinationType(), requestDto.getDestinationName()).forEach(hotel -> {
            List<HotelRoom> roomsMatchedByRequest = findRoomsBy(hotel, requestDto);

            if(!roomsMatchedByRequest.isEmpty()) {
                hotelSearchResults.add(converter.convertToHotelSearchResponseDto(hotel, roomsMatchedByRequest));
            }
        });

        return hotelSearchResults;
    }

    private List<HotelRoom> findRoomsBy(Hotel hotel, HotelSearchRequestDto requestDto){
        Map<RoomType, List<HotelRoom>> availableRoomsByType = hotel.getHotelRooms()
                .stream()
                .filter(room -> !isRoomBooked(room.getId(), requestDto.getCheckInDate(), requestDto.getCheckOutDate()))
                .collect(Collectors.groupingBy(HotelRoom::getRoomType));

        return availableRoomsByType.keySet()
                .stream()
                .filter(roomType -> availableRoomsByType.get(roomType).size() >= requestDto.getNumOfRoom())
                .map(availableRoomsByType::get)
                .flatMap(List::stream)
                .filter(room -> room.getMaxNumOfGuest() >= requestDto.getNumOfGuest())
                .collect(Collectors.toList());
    }

    private boolean isRoomBooked(Long roomId, LocalDate checkInDate, LocalDate checkOutDate){
        return hotelBookingRepository.existsByHotelRoomAndDate(roomId, checkInDate, checkOutDate);
    }
}
