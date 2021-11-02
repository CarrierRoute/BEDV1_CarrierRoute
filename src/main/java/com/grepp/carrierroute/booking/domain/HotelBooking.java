package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.exception.hotel.ErrorMessage;
import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.exception.booking.InvalidHotelBookingParameterException;
import com.grepp.carrierroute.user.domain.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotel_booking")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HotelBooking extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_room_id", referencedColumnName = "id")
    private HotelRoom hotelRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "guest_number", nullable = false)
    private int guestNumber;

    @Column(name = "price", nullable = false)
    private long price;

    @Column(name = "check_in_date", nullable = false)
    private LocalDate checkInDate;

    @Column(name = "check_out_date", nullable = false)
    private LocalDate checkOutDate;

    @Column(name = "additional_request", length = 200)
    private String additionalRequest;

    @Builder
    public HotelBooking(@NonNull User user,
                        @NonNull HotelRoom hotelRoom,
                        int guestNumber,
                        long price,
                        @NonNull LocalDate checkInDate,
                        @NonNull LocalDate checkOutDate,
                        String additionalRequest) {

        if(!isValid(guestNumber, price)){
            throw new InvalidHotelBookingParameterException(ErrorMessage.INVALID_HOTEL_BOOKING_PARAMTER);
        }

        this.user = user;
        this.hotelRoom = hotelRoom;
        this.price = price;
        this.guestNumber = guestNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.additionalRequest = additionalRequest;
    }

    private boolean isValid(int guestNumber, long price){
        return (guestNumber > 0) && (price > 0);
    }
}
