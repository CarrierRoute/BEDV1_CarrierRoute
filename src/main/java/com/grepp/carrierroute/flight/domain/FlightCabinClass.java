package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "flight_cabin_class")
public class FlightCabinClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id", referencedColumnName = "id")
    private Flight flight;

    @Column(name = "cabin_class", length = 20, nullable = false)
    private CabinClass cabinClass;

    @Column(name = "seat_count", nullable = false)
    private Long seatCount;

    @Column(name = "seat_cost", nullable = false)
    private Long seatCost;

    protected FlightCabinClass(){

    }

    public void setFlight(Flight flight) {
        if(Objects.nonNull(this.flight)) {
            this.flight.getFlightCabinClasses().remove(this);
        }
        this.flight = flight;
        flight.getFlightCabinClasses().add(this);
    }
}
