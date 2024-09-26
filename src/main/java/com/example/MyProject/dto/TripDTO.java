package com.example.MyProject.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    private String fromLocation;
    private String toLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String autoNumber;
    private String purpose;
    private BigDecimal fuelCost;
    private UUID driverId;
    private UUID workerId;

}
