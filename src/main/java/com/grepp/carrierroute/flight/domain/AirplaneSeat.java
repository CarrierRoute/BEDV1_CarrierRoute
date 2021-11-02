package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;

@Table(name = "airplane_seat")
@Entity
public class AirplaneSeat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_ID", referencedColumnName = "id")
    private Airplane airplane;

    @Column(name = "cabin_class", nullable = false)
    private CabinClass cabinClass;

    @Column(name = "seat_num", nullable = false)
    private int seatNum;

    protected AirplaneSeat(){

    }

    // GETTER
    public Long getId() {
        return id;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public CabinClass getCabinClass() {
        return cabinClass;
    }

    public int getSeatNum() {
        return seatNum;
    }
}