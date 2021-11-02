package com.grepp.carrierroute.booking.domain;

import com.grepp.carrierroute.hotel.domain.HotelRoom;
import com.grepp.carrierroute.hotel.exception.ErrorMessage;
import com.grepp.carrierroute.booking.exception.InvalidHotelBookingParameterException;
import com.grepp.carrierroute.user.domain.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "hotel_booking")
@Getter
@NoArgsConstructor
public class HotelBooking {
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
                        @NonNull LocalDate checkInDate,
                        @NonNull LocalDate checkOutDate,
                        String additionalRequest) {

        if(!isValid(guestNumber)){
            throw new InvalidHotelBookingParameterException(ErrorMessage.INVALID_HOTEL_BOOKING_PARAMTER);
        }

        this.user = user;
        this.hotelRoom = hotelRoom;
        this.guestNumber = guestNumber;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.additionalRequest = additionalRequest;
    }

    private boolean isValid(int guestNumber){
        return (guestNumber > 0);
    }
}
