package com.example.MyProject.repository;

import com.example.MyProject.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TripRepository extends JpaRepository<Trip, UUID> {

    @Query(nativeQuery = true, value = "select * from trip where driver_id = :driver_id")
    List<Trip> findByDriverId(@Param("driver_id") String userId);

    @Query(nativeQuery = true, value = "select * from trip where worker_id = :worker_id")
    List<Trip> findByWorkerId(@Param("worker_id") String userId);

    @Query(nativeQuery = true, value = "SELECT t.driver_id, SUM(t.fuel_cost) as totalFuelCost\n" +
            "FROM Trip t GROUP BY t.driver_id, t.driver_id ORDER BY totalFuelCost DESC")
    List<Object[]> findTopDriversByFuelCost();
}
