package com.grepp.carrierroute.common.domain;


import com.grepp.carrierroute.flight.domain.Airline;
import com.grepp.carrierroute.flight.domain.FlightCabinClass;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<City> cities = new ArrayList<>();

    protected Country(){

    }

    public void addCity(City city) {
        city.setCountry(this);
    }

    // GETTER
    public List<City> getCities() {
        return cities;
    }
}
