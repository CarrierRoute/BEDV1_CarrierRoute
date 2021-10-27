package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.CarBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface CarBookingRepository extends JpaRepository<CarBooking, Long> {

    @Query(value = "select distinct cb.car.id " +
            "from CarBooking cb " +
            "where not exists (select cb2.car.id " +
            "from CarBooking cb2 " +
            "where cb2.period.startDateTime between :startDateTime and :endDateTime " +
            "or cb2.period.endDateTime between :startDateTime and :endDateTime)")
    List<String> findAllByDate(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query(value = "select cb from CarBooking cb where cb.car.id = :id " +
            "and (cb.period.startDateTime between :startDateTime and :endDateTime " +
            "or cb.period.endDateTime between :startDateTime and :endDateTime)")
    Optional<CarBooking> findByIdAndDateTime(@Param("id") String id, @Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);
}
