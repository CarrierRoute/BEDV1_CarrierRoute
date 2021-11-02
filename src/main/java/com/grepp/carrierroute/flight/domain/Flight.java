package com.grepp.carrierroute.flight.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.user.domain.User;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "flight")
public class Flight extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airplane_seat_id", referencedColumnName = "id")
    private AirplaneSeat airplaneSeat;

    @Column(name = "departure_city", nullable = false)
    private String departureCity;

    @Column(name = "departure_time", nullable = false)
    private LocalDateTime departureDateTime;

    @Column(name = "arrival_city", nullable = false)
    private String arrivalCity;

    @Column(name = "arrival_time", nullable = false)
    private LocalDateTime arrivalDateTime;

    @Column(name = "cost", nullable = false)
    private long cost;

    protected Flight(){

    }

    //GETTER
    public Long getId(){ return this.id; }

    public String getDepartureCity() {
        return departureCity;
    }

    public String getArrivalCity() { return arrivalCity; }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public User getUser() { return user; }

    public AirplaneSeat getAirplaneSeat() { return airplaneSeat; }

    public long getCost() { return cost; }
}
