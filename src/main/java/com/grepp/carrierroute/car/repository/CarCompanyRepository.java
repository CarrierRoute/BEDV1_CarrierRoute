package com.grepp.carrierroute.car.repository;

import com.grepp.carrierroute.car.domain.CarCompany;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarCompanyRepository extends JpaRepository<CarCompany, Long> {
}
