package com.grepp.carrierroute.common.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "airport")
public class Airport extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    private City city;

    protected Airport(){

    }

}
