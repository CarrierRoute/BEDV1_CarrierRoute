package com.grepp.carrierroute.flight.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "airline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Airline extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "항공사 이름은 존재해야 합니다.")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "is_cancellation_allowed", nullable = false)
    private boolean isCancellationAllowed;

    @Max(value = 100, message = "환불 퍼센트는 100이하 이어야 합니다.")
    @Min(value = 0, message = "환불 퍼센트는 0이상 이어야 합니다.")
    @Column(name = "refund_percentage", nullable = false)
    private int refundPercentage;

    @Builder
    public Airline(String name, boolean isCancellationAllowed, int refundPercentage){
        this.name = name;
        this.isCancellationAllowed = isCancellationAllowed;
        this.refundPercentage = refundPercentage;
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

    public String getName() {
        return name;
    }
}