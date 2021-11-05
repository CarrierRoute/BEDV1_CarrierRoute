package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface FlightBookingRepository extends JpaRepository<Flight, Long> {

    @Query("select f from Flight f where " +
            "f.departureCity = :departureCity AND " +
            "f.departureDateTime = :departureDateTime AND " +
            "f.arrivalCity = :arrivalCity AND " +
            "f.arrivalDateTime = :arrivalDateTime AND " +
            "f.airplaneSeat.cabinClass = :cabinClass AND " +
            "f.airplaneSeat.airplane.id = :airplaneId AND " +
            "f.user is null" +
            "")
    List<Flight> findAvailableFlights(@Param("departureCity")String departureCity, @Param("departureDateTime")LocalDateTime departureDateTime,
                                      @Param("arrivalCity")String arrivalCity, @Param("arrivalDateTime")LocalDateTime arrivalDateTime,
                                      @Param("cabinClass")String cabinClass, @Param("airplaneId")Long airplaneId);

    List<Flight> findAllByUser(User user);
}
