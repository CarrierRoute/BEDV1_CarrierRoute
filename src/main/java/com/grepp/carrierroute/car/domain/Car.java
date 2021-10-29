package com.grepp.carrierroute.car.domain;

import com.grepp.carrierroute.common.BaseTimeEntity;
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
public class Car extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String id;

    private String image;

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

    @Column(nullable = false)
    private String place;

    @Builder
    public Car(String id, String image, CarGrade grade, int price, int maxPassengers, boolean bookingState, CarCompany carCompany) {
        this.id = id;
        this.image = image;
        this.grade = grade;
        this.price = price;
        this.maxPassengers = maxPassengers;
        this.bookingState = bookingState;
        this.carCompany = carCompany;
    }

    public CarCompany getCarCompany() {
        return carCompany;
    }

    public String getImage() {
        return image;
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

    public boolean isBookingState() {
        return bookingState;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }
}
