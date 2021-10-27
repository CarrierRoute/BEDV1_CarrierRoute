package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.booking.domain.vo.Period;
import com.grepp.carrierroute.car.domain.Car;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CarBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id", referencedColumnName = "id")
    private Car car;

    @Embedded
    private Period period;

    @Builder
    public CarBooking(Long id, Car car, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.id = id;
        this.car = car;
        this.period = new Period(startDateTime, endDateTime);
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartDateTime() {
        return period.getStartDateTime();
    }

    public LocalDateTime getEndDateTime(){
        return period.getEndDateTime();
    }
}
