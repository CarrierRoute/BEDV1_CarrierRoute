package com.grepp.carrierroute.flight.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "airplane_seat")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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