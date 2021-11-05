package com.grepp.carrierroute.hotel.controller;

import com.grepp.carrierroute.hotel.dto.HotelSearchRequestDto;
import com.grepp.carrierroute.hotel.dto.HotelSearchResponseDto;
import com.grepp.carrierroute.hotel.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/")
public class HotelController {
    private final HotelService hotelService;

    @GetMapping("/hotels")
    public ResponseEntity<List<HotelSearchResponseDto>> getHotels(@RequestBody HotelSearchRequestDto searchRequestDto){
        List<HotelSearchResponseDto> hotels = hotelService.getHotels(searchRequestDto);

        if(hotels.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

    @GetMapping("/hotels/{hotelId}")
    public ResponseEntity<HotelSearchResponseDto> getHotel(@PathVariable Long hotelId, @RequestBody HotelSearchRequestDto searchRequestDto){
        Optional<HotelSearchResponseDto> hotel = hotelService.getHotel(hotelId, searchRequestDto);

        if(hotel.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(hotel.get(), HttpStatus.OK);
    }
}
