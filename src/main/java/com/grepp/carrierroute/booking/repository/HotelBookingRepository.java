package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.HotelBooking;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface HotelBookingRepository extends JpaRepository<HotelBooking, Long> {
    @Query("SELECT CASE WHEN COUNT(hb)>0 THEN TRUE ELSE FALSE END FROM HotelBooking hb WHERE (hb.hotelRoom.id = :roomId) AND ((:checkInDate <= hb.checkOutDate) AND (hb.checkInDate <= :checkOutDate))")
    boolean existsByHotelRoomAndDate(@Param("roomId") Long roomId, @Param("checkInDate") LocalDate checkInDate, @Param("checkOutDate") LocalDate checkOutDate);

    List<HotelBooking> findAllByUser(User user);
}
