package com.grepp.carrierroute.common.repository;

import com.grepp.carrierroute.common.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City,Long> {
}
