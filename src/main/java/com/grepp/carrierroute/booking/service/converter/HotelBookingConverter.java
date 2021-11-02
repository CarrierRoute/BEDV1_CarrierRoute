package com.grepp.carrierroute.booking.service.converter;

import com.grepp.carrierroute.booking.domain.HotelBooking;
import com.grepp.carrierroute.booking.dto.HotelBookingRequestDto;
import com.grepp.carrierroute.booking.dto.HotelBookingResponseDto;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class HotelBookingConverter {
    public HotelBookingResponseDto convertToHotelBookingResponseDto(long totalPrice,
                                                                    List<HotelBooking> bookings,
                                                                    HotelBookingRequestDto requestDto)
    {
        return HotelBookingResponseDto.builder()
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .totalPrice(totalPrice)
                .numberOfGuestPerRoom(requestDto.getNumOfGuestPerRoom())
                .bookingIdListPerRoom(
                        bookings.stream()
                                .map(HotelBooking::getId)
                                .collect(Collectors.toList())
                )
                .build();
    }

    public HotelBooking convertHotelBooking(User user, HotelRoom room, HotelBookingRequestDto requestDto){
        return HotelBooking.builder()
                .user(user)
                .hotelRoom(room)
                .guestNumber(requestDto.getNumOfGuestPerRoom())
                .checkInDate(requestDto.getCheckInDate())
                .checkOutDate(requestDto.getCheckOutDate())
                .additionalRequest(requestDto.getAdditionalRequest())
                .build();
    }
}
