package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.booking.domain.vo.Period;
import com.grepp.carrierroute.car.domain.Car;
import com.grepp.carrierroute.user.domain.User;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Embedded
    private Period period;

    @Column(nullable = false)
    private String place;

    @Builder
    public CarBooking(Long id, Car car, User user, LocalDateTime startDateTime, LocalDateTime endDateTime, String place) {
        this.id = id;
        this.car = car;
        this.user = user;
        this.place = place;
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

    public Car getCar() {
        return car;
    }

    public User getUser() {
        return user;
    }

    public String getPlace() {
        return place;
    }
}
