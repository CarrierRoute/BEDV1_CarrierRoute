package com.grepp.carrierroute.car.domain;

import lombok.Getter;

@Getter
public enum CarGrade {
    MINI("Mini"),
    LIGHT("Light"),
    SMALL("Small"),
    MIDSIZE("Mid-Size"),
    FULL("Full-Size"),
    PREMIUM("Premium");

    private String grade;

    CarGrade(String grade) {
        this.grade = grade;
    }
}
