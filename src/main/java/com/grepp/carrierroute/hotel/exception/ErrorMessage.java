package com.grepp.carrierroute.hotel.exception;

public enum ErrorMessage {
    USER_NOT_FOUNDED("사용자 정보가 없습니다."),
    HOTEL_NOT_FOUNDED("호텔 정보가 없습니다."),
    HOTEL_ROOM_NOT_FOUNDED("호텔 방 정보가 없습니다."),
    HOTEL_BOOKING_NOT_FOUNDED("호텔 예약 정보가 정보가 없습니다."),
    HOTEL_NOT_FOUNDED_MATCHED_BY_REQUEST("조건에 해당하는 호텔 정보가 없습니다."),
    INVALID_HOTEL_ROOM_PARAMETER("호텔 방의 설정 값이 잘못 입력되었습니다."),
    INVALID_HOTEL_SEARCH_PARAMTER("호텔 조회 설정 값이 잘못 입력되었습니다."),
    INVALID_HOTEL_BOOKING_PARAMTER("호텔 예약 설정 값이 잘못 입력되었습니다."),
    INSUFFICIENT_HOTEL_ROOM_TO_BOOK("예약 가능한 객실이 부족합니다.");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage(){
        return this.message;
    }
}
