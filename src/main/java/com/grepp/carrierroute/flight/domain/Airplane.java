package com.grepp.carrierroute.flight.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "airplane")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    @Column(name = "name", nullable = false)
    private String name;

    // GETTER

    public Long getId() {
        return id;
    }

    public Airline getAirline() {
        return airline;
    }

    public String getName() {
        return name;
    }
}