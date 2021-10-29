package com.grepp.carrierroute.flight.domain;

public enum CabinClass {
    ECONOMY("CCC_001"),
    BUSINESS("CCC_002"),
    FIRST("CCC_003");

    private final String cabinClassCode;

    CabinClass(String cabinClassCode){
        this.cabinClassCode = cabinClassCode;
    }

    public String getCode(){
        return this.cabinClassCode;
    }
}
