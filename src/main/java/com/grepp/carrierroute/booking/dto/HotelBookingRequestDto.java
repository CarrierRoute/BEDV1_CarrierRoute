package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.booking.exception.InvalidHotelBookingParameterException;
import com.grepp.carrierroute.hotel.domain.RoomType;
import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class HotelBookingRequestDto {
    LocalDate checkInDate;
    LocalDate checkOutDate;
    List<Long> candidateIdListOfRoom;
    RoomType roomType;
    int numOfGuestPerRoom;
    int numOfRoom;
    String additionalRequest;

    @Builder
    public HotelBookingRequestDto(LocalDate checkInDate, LocalDate checkOutDate, List<Long> candidatesOfRoom, RoomType roomType, int numOfGuest, int numOfRoom, String additionalRequest) {
        if(!isValid(numOfGuest, numOfRoom)){
            throw new InvalidHotelBookingParameterException(ErrorMessage.INVALID_HOTEL_BOOKING_PARAMTER);
        }

        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.candidateIdListOfRoom = candidatesOfRoom;
        this.roomType = roomType;
        this.numOfGuestPerRoom = numOfGuest;
        this.numOfRoom = numOfRoom;
        this.additionalRequest = additionalRequest;
    }

    private boolean isValid(int numOfGuest, int numOfRoom){
        return (numOfGuest > 0 ) && (numOfRoom > 0);
    }
}
