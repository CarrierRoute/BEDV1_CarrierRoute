package com.grepp.carrierroute.hotel.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.exception.hotel.ErrorMessage;
import com.grepp.carrierroute.exception.hotel.InvalidHotelRoomParameterException;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class HotelRoom extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "max_guests", nullable = false)
    private int maxNumOfGuest;

    @Column(name = "price_per_day", nullable = false)
    private long pricePerDay;

    @Column(name = "photo_url", length = 100)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Builder
    public HotelRoom(@NonNull RoomType roomType,
                     int maxNumOfGuest,
                     long pricePerDay,
                     String photoUrl,
                     @NonNull Hotel hotel)
    {
        if(!isValid(maxNumOfGuest, pricePerDay)){
            throw new InvalidHotelRoomParameterException(ErrorMessage.INVALID_HOTEL_ROOM_PARAMETER);
        }

        this.roomType = roomType;
        this.maxNumOfGuest = maxNumOfGuest;
        this.pricePerDay = pricePerDay;
        this.photoUrl = photoUrl;

        this.setHotel(hotel);
    }

    private boolean isValid(int maxGuestNumber, long pricePerDay) {
        return (maxGuestNumber > 0) && (pricePerDay > 0);
    }

    private void setHotel(Hotel hotel){
        if(Objects.nonNull(this.hotel)){
            hotel.getHotelRooms().remove(this);
        }

        this.hotel = hotel;
        hotel.getHotelRooms().add(this);
    }
}
