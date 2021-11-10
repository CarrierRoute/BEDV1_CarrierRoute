package com.grepp.carrierroute.flight.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "airplane")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airline_id", referencedColumnName = "id")
    private Airline airline;

    @NotNull(message = "비행기 이름은 존재해야 합니다.")
    @Column(name = "name", nullable = false)
    private String name;

    // GETTER

    public Long getId() {
        return id;
    }

    public Airline getAirline() {
        return airline;
    }

    public String getName() {
        return name;
    }
}