package com.example.MyProject.repository;

import com.example.MyProject.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<Car, UUID> {

    @Query(nativeQuery = true, value = "select * from vehicle where driver_id = :driver_id")
    List<Car> getCarByDriverId(@Param("driver_id") String userId);

}
