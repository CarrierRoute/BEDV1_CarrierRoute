package com.grepp.carrierroute.common;

import lombok.Getter;

@Getter
public enum RefundPolicy {
    R01(10, 30, 50, 100),
    R02(5, 10, 30, 50);

    private final int oneDayRefundPercent;
    private final int threeDaysRefundPercent;
    private final int fiveDaysRefundPercent;
    private final int tenDaysRefundPercent;

    RefundPolicy(int oneDayRefundPercent, int threeDaysRefundPercent, int fiveDaysRefundPercent, int tenDaysRefundPercent) {
        this.oneDayRefundPercent = oneDayRefundPercent;
        this.threeDaysRefundPercent = threeDaysRefundPercent;
        this.fiveDaysRefundPercent = fiveDaysRefundPercent;
        this.tenDaysRefundPercent = tenDaysRefundPercent;
    }

    public long calculateRefundPrice(long price, long remainingDays) {
        if (remainingDays > 10) {
            return price;
        }

        if (remainingDays > 5) {
            return price * fiveDaysRefundPercent / 100;
        }

        if (remainingDays > 3) {
            return price * threeDaysRefundPercent / 100;
        }

        if (remainingDays >= 1) {
            return price * oneDayRefundPercent / 100;
        }

        return 0;
    }
}
