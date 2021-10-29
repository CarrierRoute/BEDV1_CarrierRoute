package com.grepp.carrierroute.common.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", referencedColumnName = "id")
    private Country country;

    protected City(){

    }

}
