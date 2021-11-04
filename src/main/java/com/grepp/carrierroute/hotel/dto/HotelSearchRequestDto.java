package com.grepp.carrierroute.hotel.dto;

import com.grepp.carrierroute.exception.hotel.InvalidHotelSearchParameterException;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

import java.time.LocalDate;

@Getter
public class HotelSearchRequestDto {
    private DestinationType destinationType;
    private String destinationName;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numOfGuest;
    private int numOfRoom;

    @Builder
    public HotelSearchRequestDto(@NonNull DestinationType destinationType,
                                 @NonNull String destinationName,
                                 @NonNull LocalDate checkInDate,
                                 @NonNull LocalDate checkOutDate,
                                 int numOfGuest,
                                 int numOfRoom)
    {
        if(!isValid(numOfGuest, numOfRoom, checkInDate, checkOutDate)){
            throw new InvalidHotelSearchParameterException();
        }

        this.destinationType = destinationType;
        this.destinationName = destinationName;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfGuest = numOfGuest;
        this.numOfRoom = numOfRoom;
    }

    private boolean isValid(int guestNumber, int numOfRoom, LocalDate checkInDate, LocalDate checkOutDate){
        return (guestNumber > 0) && (numOfRoom > 0) && (checkOutDate.isAfter(checkInDate));
    }
}
