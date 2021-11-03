package com.grepp.carrierroute.flight.repository;

import com.grepp.carrierroute.flight.domain.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight,Long> {

    @Query(value = "select f from Flight f INNER JOIN FETCH f.airplaneSeat aps " +
            "WHERE function('date_format', f.departureDateTime, '%Y-%m-%d') = :departureDate AND " +
            "f.departureCity = :departureCity AND " +
            "f.arrivalCity = :arrivalCity AND " +
            "aps.cabinClass >= :cabinClass AND " +
            "f.user.id IS NULL")
    List<Flight> findFlightsByOneway(@Param("departureCity")String departureCity, @Param("departureDate")LocalDate departureDate,
                                     @Param("arrivalCity")String arrivalCity, @Param("cabinClass")String cabinClass);

    @Query(value = "select f from Flight f INNER JOIN FETCH f.airplaneSeat aps " +
            "WHERE function('date_format', f.departureDateTime, '%Y-%m-%d') = :departureDate AND " +
            "f.departureCity = :departureCity AND " +
            "f.arrivalCity = :arrivalCity AND " +
            "aps.cabinClass >= :cabinClass AND " +
            "f.user.id IS NULL")
    List<Flight> findDepartureFlightsByRound(@Param("departureCity")String departureCity, @Param("departureDate")LocalDate departureDate,
                                             @Param("arrivalCity") String arrivalCity, @Param("cabinClass")String cabinClass);

    @Query(value = "select f from Flight f INNER JOIN FETCH f.airplaneSeat aps " +
            "WHERE function('date_format', f.arrivalDateTime, '%Y-%m-%d') = :arrivalDate AND " +
            "f.departureCity = :departureCity AND " +
            "f.arrivalCity = :arrivalCity AND " +
            "aps.cabinClass >= :cabinClass AND " +
            "f.user.id IS NULL")
    List<Flight> findArrivalFlightsByRound(@Param("arrivalCity")String arrivalCity, @Param("arrivalDate")LocalDate arrivalDate,
                                           @Param("departureCity")String departureCity, @Param("cabinClass")String cabinClass);
}
