package com.grepp.carrierroute.flight.dto;

import lombok.Getter;

import java.util.function.Supplier;

@Getter
public class SearchType {

    private final String searchTypeName;
    private final FlightSearchType flightSearchType;

    public SearchType(String searchTypeName){
        this.searchTypeName = searchTypeName;
        this.flightSearchType = FlightSearchType.create(searchTypeName);
    }

}
