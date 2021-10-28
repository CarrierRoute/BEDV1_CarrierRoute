package com.grepp.carrierroute.hotel.domain;

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
    private Integer count;

    @Column(name = "max_guests", nullable = false)
    private Integer maxGuestNumber;

    @Column(name = "price_per_day", nullable = false)
    private Long pricePerDay;

    @Column(name = "photo_url", length = 100)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id", referencedColumnName = "id")
    private Hotel hotel;

    @Builder
    public HotelRoom(@NonNull RoomType roomType,
                     @NonNull Integer count,
                     @NonNull Integer maxGuestNumber,
                     @NonNull Long pricePerDay,
                     String photoUrl,
                     @NonNull Hotel hotel)
    {
        this.roomType = roomType;
        this.count = count;
        this.maxGuestNumber = maxGuestNumber;
        this.pricePerDay = pricePerDay;
        this.photoUrl = photoUrl;

        this.setHotel(hotel);
    }

    private void setHotel(Hotel hotel){
        if(Objects.nonNull(this.hotel)){
            hotel.getHotelRooms().remove(this);
        }

        this.hotel = hotel;
        hotel.getHotelRooms().add(this);
    }
}
