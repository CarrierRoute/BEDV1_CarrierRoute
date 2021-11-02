package com.grepp.carrierroute.flight.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import javax.persistence.*;

@Entity
@Table(name = "airline")
public class Airline extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "is_cancellation_allowed", nullable = false)
    private boolean isCancellationAllowed;

    @Column(name = "refund_policy", nullable = false)
    private int refundPercentage;

    protected Airline(){

    }

    // getter

    public Long getId() {
        return id;
    }

    public boolean isCancellationAllowed() {
        return isCancellationAllowed;
    }

    public int getRefundPercentage() {
        return refundPercentage;
    }
}