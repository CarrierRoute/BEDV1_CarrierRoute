package com.grepp.carrierroute.common.domain;


import com.grepp.carrierroute.common.BaseTimeEntity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country")
public class Country extends BaseTimeEntity {

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
