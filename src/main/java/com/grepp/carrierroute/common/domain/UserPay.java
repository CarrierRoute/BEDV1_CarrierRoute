package com.grepp.carrierroute.common.domain;

import com.grepp.carrierroute.flightbooking.domain.FlightBooking;
import com.grepp.carrierroute.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_pay")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserPay {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_booking_id", referencedColumnName = "id")
    private FlightBooking flightBooking;

    @Column(name = "cost", nullable = false)
    private long cost;
}
