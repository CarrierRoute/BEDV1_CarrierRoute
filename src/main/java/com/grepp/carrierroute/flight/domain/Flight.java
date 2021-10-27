package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDate departureDate;

    @Column(name = "arrival_time", nullable = false)
    private LocalDate arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    protected Flight(){

    }

    public void setAirline(Airline airline) {
        if(Objects.nonNull(this.airline)) {
            this.airline.getFlights().remove(this);
        }
        this.airline = airline;
        airline.getFlights().add(this);
    }
}
