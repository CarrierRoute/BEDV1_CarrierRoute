package com.grepp.carrierroute.flightschedule.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "airline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "is_cancellation_allowed", nullable = false)
    private boolean isCancellationAllowed;

    @Column(name = "refund_percentage", nullable = false)
    private int refundPercentage;
}
