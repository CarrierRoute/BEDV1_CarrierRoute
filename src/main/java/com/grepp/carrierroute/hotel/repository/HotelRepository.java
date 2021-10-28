package com.grepp.carrierroute.hotel.repository;

import com.grepp.carrierroute.hotel.domain.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> findByCity(String city);
    List<Hotel> findByCountry(String country);
    List<Hotel> findByName(String name);
}
