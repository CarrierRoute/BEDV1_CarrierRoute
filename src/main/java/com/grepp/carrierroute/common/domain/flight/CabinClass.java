package com.grepp.carrierroute.common.domain.flight;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cabin_class")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CabinClass {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", nullable = false, length = 20)
    private String name;
}
