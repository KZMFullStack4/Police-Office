package com.moshka.dao;

import com.moshka.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends CrudRepository<Car,Integer> {

    @Query("select car from Car car where car.model=?1")
    Car findByModel(String model);
}
