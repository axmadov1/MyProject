package com.example.MyProject.repository;

import com.example.MyProject.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query(nativeQuery = true, value = "select * from employee where email = :email")
    Employee findByEmail(@Param("email") String email);
}
