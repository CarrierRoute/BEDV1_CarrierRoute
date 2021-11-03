package com.grepp.carrierroute.hotel.domain;

import com.grepp.carrierroute.exception.hotel.ErrorMessage;
import com.grepp.carrierroute.exception.hotel.InvalidHotelRoomParameterException;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "hotel_room")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class HotelRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomType roomType;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "max_guests", nullable = false)
    private int maxGuestNumber;

    @Column(name = "price_per_day", nullable = false)
    private long pricePerDay;

    @Column(name = "photo_url", length = 100)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Builder
    public HotelRoom(@NonNull RoomType roomType,
                     int count,
                     int maxGuestNumber,
                     long pricePerDay,
                     String photoUrl,
                     @NonNull Hotel hotel)
    {
        validateHotelRoomInfo(count, maxGuestNumber, pricePerDay);

        this.roomType = roomType;
        this.count = count;
        this.maxGuestNumber = maxGuestNumber;
        this.pricePerDay = pricePerDay;
        this.photoUrl = photoUrl;

        this.setHotel(hotel);
    }

    private void validateHotelRoomInfo(int count, int maxGuestNumber, long pricePerDay) {
        if ((count <= 0) || (maxGuestNumber <= 0) || (pricePerDay <= 0)){
            throw new InvalidHotelRoomParameterException(ErrorMessage.INVALID_HOTEL_ROOM_PARAMETER);
        }
    }

    private void setHotel(Hotel hotel){
        if(Objects.nonNull(this.hotel)){
            hotel.getHotelRooms().remove(this);
        }

        this.hotel = hotel;
        hotel.getHotelRooms().add(this);
    }

    public boolean isAvailable(int guestNumber, int numOfRoom){
        return (maxGuestNumber >= guestNumber) && (count >= numOfRoom);
    }
}
