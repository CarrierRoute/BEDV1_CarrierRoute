package com.grepp.carrierroute.common.domain;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    protected Airport(){

    }
}
