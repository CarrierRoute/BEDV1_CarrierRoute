package com.grepp.carrierroute.booking.domain.vo;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class Period {

    private LocalDateTime startDateTime;

    private LocalDateTime endDateTime;

}
