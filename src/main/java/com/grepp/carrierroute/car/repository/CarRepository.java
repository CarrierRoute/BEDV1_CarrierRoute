package com.grepp.carrierroute.car.repository;

import com.grepp.carrierroute.car.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface CarRepository extends JpaRepository<Car, String> {

    @Query("select c from Car c where c.airport.code = :airport " +
            "and (c.bookingState = false or c.id not in :carIds )")
    List<Car> findByAirPortAmongNotBookedCars(@Param("airport") String airport, @Param("carIds") List<String> carIds);

    @Query("select c from Car c where c.airport.city.name = :city " +
            "and (c.bookingState = false or c.id not in :carIds )")
    List<Car> findByCityAmongNotBookedCars(@Param("city") String city, @Param("carIds") List<String> carIds);
}
