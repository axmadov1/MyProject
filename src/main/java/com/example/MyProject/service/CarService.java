package com.example.MyProject.service;

import com.example.MyProject.model.Car;
import com.example.MyProject.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car getCarById(UUID id) {
        return carRepository.findById(id).orElse(null);
    }

    public Car createCar(Car car) {
        car.setId(UUID.randomUUID());
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
}
