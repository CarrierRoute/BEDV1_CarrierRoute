package com.grepp.carrierroute.common.repository;

import com.grepp.carrierroute.common.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}
