package com.grepp.carrierroute.user.domain;

import com.grepp.carrierroute.car.domain.vo.Address;
import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.user.domain.vo.Point;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String id;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Embedded
    private Address address;

    @Embedded
    private Point point;

    @Builder
    public User(String id, String password, String phoneNumber, Address address, Point point) {
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.point = point;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return getCreatedDate() == null;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Address getAddress() {
        return address;
    }

    public Point getPoint() {
        return point;
    }
}
