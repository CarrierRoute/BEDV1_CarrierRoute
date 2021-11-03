package com.grepp.carrierroute.user.domain.vo;

import com.grepp.carrierroute.exception.booking.LackOfPointException;
import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Point {

    private long point;

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
