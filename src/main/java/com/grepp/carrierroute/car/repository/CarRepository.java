package com.grepp.carrierroute.car.repository;

import com.grepp.carrierroute.car.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select c from Car c where c.place = :place " +
            "and (c.bookingState = true or c.id in :carIds )")
    List<Car> findByPlace(@Param("place") String place,@Param("carIds") List<String> carIds);
}
