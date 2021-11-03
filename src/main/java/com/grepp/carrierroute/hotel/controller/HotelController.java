package com.grepp.carrierroute.hotel.controller;

import com.grepp.carrierroute.hotel.dto.HotelSearchRequestDto;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import com.grepp.carrierroute.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public List<HotelSearchResponseDto> getHotels(@RequestBody HotelSearchRequestDto searchRequestDto){
        return hotelService.getHotels(searchRequestDto);
    }

    @GetMapping("/hotels/{hotelId}")
    public HotelSearchResponseDto getHotel(@PathVariable Long hotelId, @RequestBody HotelSearchRequestDto searchRequestDto){
        return hotelService.getHotel(hotelId, searchRequestDto);
    }
}
