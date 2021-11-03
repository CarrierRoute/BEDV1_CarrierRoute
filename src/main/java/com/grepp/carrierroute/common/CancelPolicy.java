package com.grepp.carrierroute.common;

import lombok.Getter;

@Getter
public enum CancelPolicy {

    C00(true),
    C01(false);

    private boolean value;

    CancelPolicy(boolean value) {
        this.value = value;
    }

    public boolean isNotRefundable() {
        return !value;
    }
}
