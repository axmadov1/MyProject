package com.example.MyProject.controller;

import com.example.MyProject.model.Car;
import com.example.MyProject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Car> getAllCars() {
        return carService.getAllCars();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DRIVER')")
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Car createCar(@RequestBody Car car) {
        return carService.createCar(car);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable UUID id, @RequestBody Car carDetails) {
        return carService.updateCar(id, carDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }
}
