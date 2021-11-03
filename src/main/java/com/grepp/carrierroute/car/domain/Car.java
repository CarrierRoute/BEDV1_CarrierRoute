package com.grepp.carrierroute.car.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.common.file.UploadFile;
import com.grepp.carrierroute.common.domain.Airport;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "car")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class Car extends BaseTimeEntity {

    public static final int MAX_PRICE = 100000;
    public static final int MIN_PRICE = 1000;

    public static final int MAX_PASSENGER = 12;
    public static final int MIN_PASSENGER = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String licencePlate;

    @Embedded
    private UploadFile uploadFile;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CarGrade grade;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private int maxPassengers;

    @Column(columnDefinition = "boolean default false")
    private boolean bookingState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_company_id", referencedColumnName = "id")
    private CarCompany carCompany;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "airport_id", referencedColumnName = "id")
    private Airport airport;

    @Builder
    public Car(UploadFile uploadFile, String licencePlate, CarGrade grade, int price, int maxPassengers, boolean bookingState, CarCompany carCompany, Airport airport) {
        this.uploadFile = uploadFile;
        this.licencePlate = licencePlate;
        this.grade = grade;
        this.price = price;
        this.maxPassengers = maxPassengers;
        this.bookingState = bookingState;
        this.carCompany = carCompany;
        this.airport = airport;
    }

    public Long getId() {
        return id;
    }

    public CarCompany getCarCompany() {
        return carCompany;
    }

    public UploadFile getUploadFile() {
        return uploadFile;
    }

    public CarGrade getGrade() {
        return grade;
    }

    public int getPrice() {
        return price;
    }

    public int getMaxPassengers() {
        return maxPassengers;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public boolean isBooked() {
        return bookingState;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }
}
