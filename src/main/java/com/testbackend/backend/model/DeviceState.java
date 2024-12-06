package com.testbackend.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceState {
    private String deviceId;
    private String state;
    private SensorData sensorData;
}
