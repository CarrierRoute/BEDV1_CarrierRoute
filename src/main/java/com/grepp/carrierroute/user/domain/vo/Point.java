package com.grepp.carrierroute.user.domain.vo;

import com.grepp.carrierroute.exception.booking.LackOfPointException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Point {

    private long point;

    @Builder
    public Point(long point) {
        this.point = point;
    }

    public void subtract(long totalPrice){
        if (this.point < totalPrice) {
            throw new LackOfPointException(this.point);
        }
        this.point -= totalPrice;
    }

    public void add(long totalPrice) {
        this.point += totalPrice;
    }
}
