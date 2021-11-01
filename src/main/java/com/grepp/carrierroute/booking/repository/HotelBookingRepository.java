package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.HotelBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
    @Query("SELECT CASE WHEN COUNT(hb)>0 THEN TRUE ELSE FALSE END FROM HotelBooking hb WHERE (hb.hotelRoom.id = :roomId) AND ((:checkInDate > hb.checkInDate AND :checkInDate < hb.checkOutDate) OR (:checkOutDate > hb.checkInDate AND :checkOutDate < hb.checkOutDate))")
    boolean existsByHotelRoomAndDate(@Param("roomId") Long roomId, @Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);
}
