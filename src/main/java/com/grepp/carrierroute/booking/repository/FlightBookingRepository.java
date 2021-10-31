package com.grepp.carrierroute.booking.repository;

import com.grepp.carrierroute.booking.domain.FlightBooking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightBookingRepository extends JpaRepository<FlightBooking,Long> {
}
