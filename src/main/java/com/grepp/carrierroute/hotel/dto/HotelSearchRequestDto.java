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
    private Integer guestNumber;
    private Integer numOfRoom;

    @Builder
    public HotelSearchRequestDto(@NonNull DestinationType destinationType,
                                 @NonNull String destinationName,
                                 @NonNull LocalDate startDate,
                                 @NonNull LocalDate endDate,
                                 @NonNull int guestNumber,
                                 @NonNull int numOfRoom) throws InvalidHotelSearchParameterException, NullPointerException
    {
        validateDate(startDate, endDate);

        this.destinationType = destinationType;
        this.destinationName = destinationName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestNumber = guestNumber;
        this.numOfRoom = numOfRoom;
    }

    private void validateDate(LocalDate startDate, LocalDate endDate) throws InvalidHotelSearchParameterException {
        if(!endDate.isAfter(startDate)){
            throw new InvalidHotelSearchParameterException(ErrorMessage.INVALID_HOTEL_SEARCH_PARAMTER);
        }
    }
}
