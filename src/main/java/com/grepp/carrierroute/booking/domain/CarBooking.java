package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.booking.domain.vo.Period;
import com.grepp.carrierroute.car.domain.Car;

import javax.persistence.*;

@Entity
public class CarBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Embedded
    private Period period;


}
