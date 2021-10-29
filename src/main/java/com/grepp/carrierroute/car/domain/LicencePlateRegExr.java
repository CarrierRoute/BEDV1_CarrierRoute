package com.grepp.carrierroute.car.domain;

import lombok.Getter;

@Getter
public enum LicencePlateRegExr {

    KOREA("^([가-힣]{2})?\\s?([0-9가-힣]{1,})\\s?[가-힣]\\s?[0-9]{4}");

    private final String regExr;

    LicencePlateRegExr(String regExr) {
        this.regExr = regExr;
    }
}
