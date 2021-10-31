package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.flight.domain.Airline;
import com.grepp.carrierroute.flight.domain.Flight;
import com.grepp.carrierroute.flight.domain.FlightCabinClass;
import com.grepp.carrierroute.user.domain.User;
import lombok.Builder;

import javax.persistence.*;

@Entity
@Table(name = "flight_booking")
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_cabin_class_id", referencedColumnName = "id")
    private FlightCabinClass flightCabinClass;

    protected FlightBooking() {

    }

    @Builder
    public FlightBooking(User user, FlightCabinClass flightCabinClass) {
        this.user = user;
        this.flightCabinClass = flightCabinClass;
    }

    // GETTER
    public Long getId() {
        return id;
    }

    public FlightCabinClass getFlightCabinClass() {
        return flightCabinClass;
    }
}
