package com.grepp.carrierroute.flightschedule.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cabin_class")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CabinClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_schedule_id", referencedColumnName = "id")
    private FlightSchedule flightSchedule;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "cost", nullable = false)
    private long cost;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
