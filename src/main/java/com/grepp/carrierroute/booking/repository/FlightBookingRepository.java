package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.FlightBooking;
import com.grepp.carrierroute.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
    public List<FlightBooking> findAllByUser(User user);
}
