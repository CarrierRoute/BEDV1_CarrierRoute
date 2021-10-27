package com.grepp.carrierroute.flight.domain;

import javax.persistence.*;

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

    protected Airline(){

    }
}