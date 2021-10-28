package com.grepp.carrierroute.hotel.exception;

public enum ErrorMessage {
    HOTEL_ROOM_NOT_FOUNDED("호텔 방 정보가 없습니다."),
    INVALID_HOTEL_ROOM_PARAMETER("호텔 방의 설정 값이 잘못 입력되었습니다."),
    INVALID_HOTEL_SEARCH_PARAMTER("호텔 조회 설정 값이 잘못 입력되었습니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
