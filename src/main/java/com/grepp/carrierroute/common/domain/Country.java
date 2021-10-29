package com.grepp.carrierroute.common.domain;


import com.grepp.carrierroute.common.BaseTimeEntity;
import javax.persistence.*;

@Entity
@Table(name = "country")
public class Country extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", length = 10, nullable = false)
    private String code;

    protected Country(){

    }

}
