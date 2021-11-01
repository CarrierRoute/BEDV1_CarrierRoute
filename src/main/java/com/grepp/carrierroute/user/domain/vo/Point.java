package com.grepp.carrierroute.user.domain.vo;

import com.grepp.carrierroute.booking.exception.LackOfPointException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
@Getter
public class Point {

    private long point;

    @Builder
    public Point(long point) {
        this.point = point;
    }

    public void subtract(long totalPrice){
        if (this.point < totalPrice) {
            throw new LackOfPointException("Lack of Point. Current Point : " + this.point);
        }
        this.point -= totalPrice;
    }

    public void add(long totalPrice) {
        this.point += totalPrice;
    }
}
