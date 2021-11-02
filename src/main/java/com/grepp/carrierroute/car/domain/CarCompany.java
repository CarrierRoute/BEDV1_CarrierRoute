package com.grepp.carrierroute.car.domain;

import com.grepp.carrierroute.car.domain.vo.Address;
import com.grepp.carrierroute.common.BaseTimeEntity;
import com.grepp.carrierroute.common.CancelPolicy;
import com.grepp.carrierroute.common.RefundPolicy;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "car_company")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "id")
public class CarCompany extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

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

    @Builder
    public CarCompany(String name, Address address, String telephone, CancelPolicy cancelPolicy, RefundPolicy refundPolicy) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.cancelPolicy = cancelPolicy;
        this.refundPolicy = refundPolicy;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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

}
