package com.example.MyProject.controller;

import com.example.MyProject.dto.TripDTO;
import com.example.MyProject.model.Trip;
import com.example.MyProject.service.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    @Autowired
    private TripService tripService;

    @PreAuthorize("hasRole('ADMIN') or hasRole('WORKER') or hasRole('DRIVER')")
    @GetMapping
    public List<Trip> getAllTrips() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (isAdmin) return tripService.getAllTrips();


        String userId = authentication.getName();
        boolean isDriver = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_DRIVER"));
        if (isDriver) return tripService.getTripsByDriverId(userId);

        boolean isWorker = authentication.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_WORKER"));
        if (isWorker) return tripService.getTripsByWorkerId(userId);

        return List.of();

    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('WORKER') or hasRole('DRIVER')")
    @GetMapping("/{id}")
    public Trip getTripById(@PathVariable UUID id) {
        return tripService.getTripById(id);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DRIVER')")
    @PostMapping
    public Trip createTrip(@RequestBody TripDTO tripDTO) {
        return tripService.createTrip(tripDTO);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('DRIVER')")
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
