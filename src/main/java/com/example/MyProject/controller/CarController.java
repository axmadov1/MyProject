package com.example.MyProject.controller;
import com.example.MyProject.dto.CarDTO;
import com.example.MyProject.model.Car;
import com.example.MyProject.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('DRIVER')")
    @GetMapping
    public List<Car> getAllCars() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) return carService.getAllCars();

        String userId = authentication.getName();
        boolean isDriver = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_DRIVER"));
        if (isDriver) return carService.getCarByDriverId(userId);

        return List.of();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    public Car getCarById(@PathVariable UUID id) {
        return carService.getCarById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public Car createCar(@RequestBody CarDTO carDTO) {
        return carService.createCar(carDTO);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public Car updateCar(@PathVariable UUID id, @RequestBody Car carDetails) {
        return carService.updateCar(id, carDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{carId}/assign-driver")
    public Car assignDriverToCar(@PathVariable UUID carId, @RequestBody UUID driverId){
        return carService.assignDriverToCar(carId, driverId);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable UUID id) {
        carService.deleteCar(id);
    }
}
