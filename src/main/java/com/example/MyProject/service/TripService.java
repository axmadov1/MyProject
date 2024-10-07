package com.example.MyProject.service;

import com.example.MyProject.dto.TripDTO;
import com.example.MyProject.model.Car;
import com.example.MyProject.model.Employee;
import com.example.MyProject.model.Trip;
import com.example.MyProject.repository.CarRepository;
import com.example.MyProject.repository.EmployeeRepository;
import com.example.MyProject.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(UUID id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Trip createTrip(TripDTO tripDTO) {
        boolean existsCar = carRepository.existsByAutoNumber(tripDTO.getAutoNumber());

        if (!existsCar) {
            throw new RuntimeException("Car with auto number " + tripDTO.getAutoNumber() + " not found");
        }

        Employee driver = employeeRepository.findById(tripDTO.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        Employee worker = employeeRepository.findById(tripDTO.getWorkerId())
                .orElseThrow(() -> new RuntimeException("Worker not found"));

        Trip trip = new Trip();
        trip.setId(UUID.randomUUID());
        trip.setFromLocation(tripDTO.getFromLocation());
        trip.setToLocation(tripDTO.getToLocation());
        trip.setStartTime(tripDTO.getStartTime());
        trip.setEndTime(tripDTO.getEndTime());
        trip.setAutoNumber(tripDTO.getAutoNumber());
        trip.setPurpose(tripDTO.getPurpose());
        trip.setFuelCost(tripDTO.getFuelCost());
        trip.setDriver(driver);
        trip.setWorker(worker);

        return tripRepository.save(trip);
    }

    public Trip updateTrip(UUID id, Trip tripDetails){
        Trip trip = tripRepository.findById(id).orElse(null);
        if (trip != null){
            trip.setFromLocation(tripDetails.getFromLocation());
            trip.setToLocation(tripDetails.getToLocation());
            trip.setStartTime(tripDetails.getStartTime());
            trip.setEndTime(tripDetails.getEndTime());
            trip.setPurpose(tripDetails.getPurpose());
            trip.setFuelCost(tripDetails.getFuelCost());
            trip.setDriver(tripDetails.getDriver());
            trip.setWorker(tripDetails.getWorker());

            return tripRepository.save(trip);
        }
        return null;
    }

    public void deleteTrip(UUID id) {
        tripRepository.deleteById(id);
    }

    public List<Trip> getTripsByDriverId(String userId) {
        return tripRepository.findByDriverId(userId);
    }

    public List<Trip> getTripsByWorkerId(String userId) {
        return tripRepository.findByWorkerId(userId);
    }

    public List<Object[]> getTopDriversByFuelCost() {
        return tripRepository.findTopDriversByFuelCost();
    }
}
