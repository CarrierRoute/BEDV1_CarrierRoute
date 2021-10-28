package com.grepp.carrierroute.car.domain;

import com.grepp.carrierroute.car.domain.vo.Address;
import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.common.CancelPolicy;
import com.grepp.carrierroute.common.RefundPolicy;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;

@Entity
@Table(name = "car_company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class CarCompany extends BaseTimeEntity implements Persistable<String> {

    @Id
    private String id;

    @Embedded
    private Address address;

    @Column(nullable = false)
    private String telephone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CancelPolicy cancelPolicy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RefundPolicy refundPolicy;

    public Address getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public CancelPolicy getCancelPolicy() {
        return cancelPolicy;
    }

    public RefundPolicy getRefundPolicy() {
        return refundPolicy;
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public boolean isNew() {
        return false;
    }
}
