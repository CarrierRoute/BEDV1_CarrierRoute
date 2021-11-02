package com.grepp.carrierroute.hotel.dto;

import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import com.grepp.carrierroute.hotel.exception.InvalidHotelSearchParameterException;
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
                                 @NonNull LocalDate startDate,
                                 @NonNull LocalDate endDate,
                                 int numOfGuest,
                                 int numOfRoom)
    {
        if(!isValid(numOfGuest, numOfRoom, startDate, endDate)){
            throw new InvalidHotelSearchParameterException(ErrorMessage.INVALID_HOTEL_SEARCH_PARAMTER);
        }

        this.destinationType = destinationType;
        this.destinationName = destinationName;
        this.checkInDate = startDate;
        this.checkOutDate = endDate;
        this.numOfGuest = numOfGuest;
        this.numOfRoom = numOfRoom;
    }

    private boolean isValid(int guestNumber, int numOfRoom, LocalDate startDate, LocalDate endDate){
        return (guestNumber > 0) && (numOfRoom > 0) && (endDate.isAfter(startDate));
    }
}
