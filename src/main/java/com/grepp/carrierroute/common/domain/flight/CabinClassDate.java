package com.grepp.carrierroute.common.domain.flight;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cabin_class_date")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CabinClassDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_schedule_id", referencedColumnName = "id")
    private FlightSchedule flightSchedule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabin_class_id", referencedColumnName = "id")
    private CabinClass cabinClass;

    @Column(name = "cost", nullable = false)
    private long cost;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
