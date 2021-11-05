package com.grepp.carrierroute.booking.dto;

import com.grepp.carrierroute.exception.booking.InvalidHotelBookingParameterException;
import com.grepp.carrierroute.hotel.domain.RoomType;
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
    public HotelBookingRequestDto(LocalDate checkInDate, LocalDate checkOutDate, List<Long> candidateIdListOfRoom, RoomType roomType, int numOfGuestPerRoom, int numOfRoom, String additionalRequest) {
        if(!isValid(numOfGuestPerRoom, numOfRoom, checkInDate, checkOutDate)){
            throw new InvalidHotelBookingParameterException();
        }

        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.candidateIdListOfRoom = candidateIdListOfRoom;
        this.roomType = roomType;
        this.numOfGuestPerRoom = numOfGuestPerRoom;
        this.numOfRoom = numOfRoom;
        this.additionalRequest = additionalRequest;
    }

    private boolean isValid(int numOfGuestPerRoom, int numOfRoom, LocalDate checkInDate, LocalDate checkOutDate){
        return (numOfGuestPerRoom > 0 ) && (numOfRoom > 0) && (checkOutDate.isAfter(checkInDate));
    }
}
