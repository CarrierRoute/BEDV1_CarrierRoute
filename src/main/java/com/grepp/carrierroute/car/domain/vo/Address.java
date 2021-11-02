package com.grepp.carrierroute.car.domain.vo;

import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class Address {

    @Column(nullable = false)
    private int zipcode;

    @Column(nullable = false)
    private String state;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String detail;

    @Column(nullable = false)
    private String country;

    @Builder
    public Address(int zipcode, String state, String city, String detail, String country) {
        this.zipcode = zipcode;
        this.state = state;
        this.city = city;
        this.detail = detail;
        this.country = country;
    }
}
