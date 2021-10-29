package com.grepp.carrierroute.flight.repository;

import com.grepp.carrierroute.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {
    @Query(value = "select f.id from Flight f left join FlightCabinClass fcc ON f.id = fcc.flight.id " +
            "WHERE function('date_format', f.departureDateTime, '%Y-%m-%d') = :departureDate AND " +
            "f.departureCity = :departureCity AND " +
            "f.arrivalCity = :arrivalCity AND " +
            "fcc.seatCount >= :headCount AND " +
            "fcc.cabinClass = :cabinClass")
    List<Long> findFlightsBy(@Param("departureCity")String departureCity, @Param("departureDate")LocalDate departureDate,
                             @Param("arrivalCity")String arrivalCity, @Param("headCount")Long headCount,
                             @Param("cabinClass")String cabinClass);
}
