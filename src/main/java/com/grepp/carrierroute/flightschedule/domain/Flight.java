package com.grepp.carrierroute.flightschedule.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flight")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    @Column(name = "name", nullable = false, length = 50)
    private String name;
}
