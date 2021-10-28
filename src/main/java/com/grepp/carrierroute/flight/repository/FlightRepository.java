package com.grepp.carrierroute.flight.repository;

import com.grepp.carrierroute.flight.domain.CabinClass;
import com.grepp.carrierroute.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select f.id from flight f left join flight_cabin_class fcc ON f.id = fcc.flight_id " +
            "WHERE function(\"date_format\", f.departure_time, '%Y-%m-%d') = :departureDate AND " +
            "f.departure_city = :departureCity AND " +
            "f.arrival_city = :arrivalCity AND " +
            "fcc.seat_cnt >= :headCount AND " +
            "fcc.cabin_class = :cabinClass")
    List<Long> findFlightsBy(@Param("departureCity")String departureCity, @Param("departureDate")LocalDate departureDate,
                             @Param("arrivalCity")String arrivalCity, @Param("headCount")Long headCount,
                             @Param("cabinClass")String cabinClass);
}
