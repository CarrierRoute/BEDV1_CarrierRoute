package com.grepp.carrierroute.flightbooking.domain;

import com.grepp.carrierroute.flightschedule.domain.CabinClass;
import com.grepp.carrierroute.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "flight_booking")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FlightBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cabin_class_id", referencedColumnName = "id")
    private CabinClass cabinClass;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;
}
