package com.grepp.carrierroute.common.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Airport> airports = new ArrayList<>();

    protected City(){

    }

    public void setCountry(Country country) {
        if(Objects.nonNull(this.country)) {
            this.country.getCities().remove(this);
        }
        this.country = country;
        country.getCities().add(this);
    }

    public void addAirport(Airport airport) {
        airport.setCity(this);
    }

    // GETTER
    public List<Airport> getAirports() {
        return airports;
    }
}
