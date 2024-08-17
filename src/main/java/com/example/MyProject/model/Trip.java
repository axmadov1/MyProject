package com.example.MyProject.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(name = "from_location", nullable = false)
    private String fromLocation;
    @Column(name = "to_location", nullable = false)
    private String toLocation;
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
    @Column(name = "purpose", nullable = false)
    private String purpose;
    @Column(name = "fuel_cost", nullable = false)
    private BigDecimal fuelCost;

    @ManyToOne
    @JoinColumn(name = "driver_id", nullable = false)
    private Employee driver;

    @ManyToOne
    @JoinColumn(name = "worker_id", nullable = false)
    private Employee worker;


}
