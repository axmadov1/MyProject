package com.example.MyProject.service;

import com.example.MyProject.dto.CarDTO;
import com.example.MyProject.model.Car;
import com.example.MyProject.model.Employee;
import com.example.MyProject.repository.CarRepository;
import com.example.MyProject.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(CarDTO carDTO) {
        Employee driver = employeeRepository.findById(carDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Car car = new Car();
        car.setId(UUID.randomUUID());
        car.setMake(carDTO.getMake());
        car.setModel(carDTO.getModel());
        car.setYear(carDTO.getYear());
        car.setAutoNumber(carDTO.getAutoNumber());
        car.setDriver(driver);

        return carRepository.save(car);
    }

    public Car updateCar(UUID id, Car carDetails){
        Car car = carRepository.findById(id).orElse(null);
        if (car != null){
            car.setMake(carDetails.getMake());
            car.setModel(carDetails.getModel());
            car.setYear(carDetails.getYear());
            car.setAutoNumber(carDetails.getAutoNumber());
            car.setDriver(carDetails.getDriver());
            return carRepository.save(car);
        }
        return null;
    }

    public void deleteCar(UUID id) {
        carRepository.deleteById(id);
    }

    public List<Car> getCarByDriverId(String userId) {
        return carRepository.getCarByDriverId(userId);
    }

    public Car assignDriverToCar(UUID carId, UUID driverId) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        Employee driver = employeeRepository.findById(driverId)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        car.setDriver(driver);
        return carRepository.save(car);
    }
}
