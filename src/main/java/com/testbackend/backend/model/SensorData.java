package com.testbackend.backend.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorData {
//    @NotNull(message = "Device ID is required")
//    private String deviceId;
//
//    @NotNull(message = "Timestamp is required")
//    private Instant timestamp;
//
//    // Sensor metrics
//    @Positive(message = "Soil humidity must be positive")
//    private Double soilHumidity;
//
//    @Positive(message = "Lux must be positive")
//    private Double lux;
//
//    @Positive(message = "Temperature must be positive")
//    private Double temperature;
//
//    @Positive(message = "CO2 level must be positive")
//    private Double co2;
//
//    @Positive(message = "Air humidity must be positive")
//    private Double airHumidity;
//
//    // Device status
//    private DeviceStatus deviceStatus;


    // esp là ng dùng duy nhất
    private Double soilHumidity;
    private Double lux;
    private Double temperature;
    private Double co2;
    private Double airHumidity;
    private DeviceStatus deviceStatus;
}