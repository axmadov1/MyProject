package com.example.MyProject.service;

import com.example.MyProject.model.Trip;
import com.example.MyProject.repository.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TripService {
    @Autowired
    private TripRepository tripRepository;

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Trip getTripById(UUID id) {
        return tripRepository.findById(id).orElse(null);
    }

    public Trip createTrip(Trip trip) {
        trip.setId(UUID.randomUUID());
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
}
