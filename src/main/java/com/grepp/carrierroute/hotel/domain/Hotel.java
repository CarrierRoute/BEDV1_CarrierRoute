package com.grepp.carrierroute.hotel.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotel")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "city", nullable = false, length = 20)
    private String city;

    @Column(name = "country", nullable = false, length = 20)
    private String country;

    @Column(name = "guest_rating", nullable = false)
    private float guestRating;

    @Column(name = "star_rating", nullable = false)
    private int starRating;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "check_in_time", nullable = false)
    private LocalTime checkInTime;

    @Column(name = "check_out_time", nullable = false)
    private LocalTime checkOutTime;

    @Column(name = "photo_url", length = 100)
    private String photoUrl;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HotelRoom> hotelRooms;

    @Builder
    public Hotel(@NonNull String name,
                 @NonNull String address,
                 @NonNull String city,
                 @NonNull String country,
                 float guestRating,
                 int starRating,
                 String description,
                 @NonNull LocalTime checkInTime,
                 @NonNull LocalTime checkOutTime,
                 String photoUrl)
    {
        this.name = name;
        this.address = address;
        this.city = city;
        this.country = country;
        this.guestRating = guestRating;
        this.starRating = starRating;
        this.description = description;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.photoUrl = photoUrl;
        this.hotelRooms = new ArrayList<>();
    }
}
