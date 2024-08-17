package com.example.MyProject.controller;

import com.example.MyProject.model.Trip;
import com.example.MyProject.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<Trip> getAllTrips() {
        return tripService.getAllTrips();
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WORKER') or hasRole('DRIVER')")
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable UUID id) {
        return tripService.getTripById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DRIVER')")
    @PostMapping
    public Trip createTrip(@RequestBody Trip trip) {
        return tripService.createTrip(trip);
    }

    @PutMapping("/{id}")
    public Trip updateTrip(@PathVariable UUID id, @RequestBody Trip tripDetails) {
        return tripService.updateTrip(id, tripDetails);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteTrip(@PathVariable UUID id) {
        tripService.deleteTrip(id);
    }
}
