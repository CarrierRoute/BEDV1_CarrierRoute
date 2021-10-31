package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.flight.domain.Airline;
import com.grepp.carrierroute.flight.domain.Flight;

import javax.persistence.*;

@Entity
@Table(name = "flight_booking")
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    protected FlightBooking(){

    }

    // GETTER
    public Long getId() {
        return id;
    }

    public Flight getFlight() {
        return flight;
    }
}
