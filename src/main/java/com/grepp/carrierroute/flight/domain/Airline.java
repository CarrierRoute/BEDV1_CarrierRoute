package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "airline")
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "cancel_policy", length = 3, nullable = false)
    private String cancelPolicy;

    @Column(name = "refund_policy", length = 3, nullable = false)
    private String refundPolicy;

    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flight> flights = new ArrayList<>();

    protected Airline(){

    }

    public void addFlight(Flight flight) {
        flight.setAirline(this);
    }

    // getter
    public List<Flight> getFlights() {
        return flights;
    }
}