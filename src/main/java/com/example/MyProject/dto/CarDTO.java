package com.example.MyProject.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    private String make;
    private String model;
    private int year;
    private String autoNumber;
    private UUID driverId;

}
