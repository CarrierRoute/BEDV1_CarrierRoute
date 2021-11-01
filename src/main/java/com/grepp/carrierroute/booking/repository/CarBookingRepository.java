package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.CarBooking;
import com.grepp.carrierroute.booking.dto.CarBookingResponseDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


public interface CarBookingRepository extends JpaRepository<CarBooking, Long> {

    @Query(value = "select cb.car.id " +
            "from CarBooking cb " +
            "where cb.period.startDateTime between :startDateTime and :endDateTime " +
            "or cb.period.endDateTime between :startDateTime and :endDateTime")
    List<String> findBookedCarIdsByDateTime(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query(value = "select cb from CarBooking cb where cb.car.id = :id " +
            "and (cb.period.startDateTime between :startDateTime and :endDateTime " +
            "or cb.period.endDateTime between :startDateTime and :endDateTime)")
    Optional<CarBooking> findByIdAndDateTime(@Param("id") String id, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query(value = "select new com.grepp.carrierroute.booking.dto.CarBookingSimple(cb.id, c.licencePlate, c.uploadFile.uploadFileName, c.uploadFile.storeFileName, cb.place, cb.period.startDateTime, cb.period.endDateTime) from CarBooking cb join cb.car c where cb.user.id = :userId")
    List<CarBookingResponseDto> findCarBookings(@Param("userId") String userId);
}
