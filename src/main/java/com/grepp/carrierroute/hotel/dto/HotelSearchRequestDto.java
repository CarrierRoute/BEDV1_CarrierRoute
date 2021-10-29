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
    private LocalDate startDate;
    private LocalDate endDate;
    private int guestNumber;
    private int numOfRoom;

    @Builder
    public HotelSearchRequestDto(@NonNull DestinationType destinationType,
                                 @NonNull String destinationName,
                                 @NonNull LocalDate startDate,
                                 @NonNull LocalDate endDate,
                                 int guestNumber,
                                 int numOfRoom)
    {
        validateSearchRequestInfo(guestNumber, numOfRoom, startDate, endDate);

        this.destinationType = destinationType;
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestNumber = guestNumber;
        this.numOfRoom = numOfRoom;
    }

    private void validateSearchRequestInfo(int guestNumber, int numOfRoom, LocalDate startDate, LocalDate endDate){
        if( (guestNumber <= 0) || (numOfRoom <= 0) || !endDate.isAfter(startDate) ){
            throw new InvalidHotelSearchParameterException(ErrorMessage.INVALID_HOTEL_SEARCH_PARAMTER);
        }
    }
}
